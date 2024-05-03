package com.smartflow.smartflow.control.informationcontroller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
import com.smartflow.smartflow.dto.informationdto.InformationFilter;
import com.smartflow.smartflow.dto.informationdto.InformationResponse;
import com.smartflow.smartflow.model.Information;
import com.smartflow.smartflow.service.informationservice.InformationService;
import com.smartflow.smartflow.specifications.InformationSpecifications;
import com.smartflow.smartflow.util.DateUtil;

@RestController
@CrossOrigin
@RequestMapping("api/v1/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @PostMapping(path = "/save")
    public String saveInfo(@ModelAttribute InformationDTO informationDTO) {
        return informationService.addInformation(informationDTO);
    }

    @GetMapping("/getInformations")
    public List<InformationResponse> searchInformations(InformationFilter filter) {
        Specification<Information> spec = Specification.where(null);

        if (StringUtils.hasText(filter.getName())) {
            spec = spec.and(InformationSpecifications.nameContains(filter.getName()));
        }

        if (StringUtils.hasText(filter.getDescription())) {
            spec = spec.and(InformationSpecifications.descriptionContains(filter.getDescription()));
        }

        try {
            if (filter.getStartDate() != null) {
                Timestamp endDate = filter.getEndDate() != null ? DateUtil.convertStringToTimestamp(filter.getEndDate())
                        : new Timestamp(System.currentTimeMillis());

                spec = spec.and(InformationSpecifications
                        .uploadDateBetween(DateUtil.convertStringToTimestamp(filter.getStartDate()), endDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (filter.getTeamId() != null && !filter.getTeamId().isEmpty()) {
            spec = spec.and(InformationSpecifications.teamIdIn(filter.getTeamId()));
        }

        if (filter.getTags() != null && !filter.getTags().isEmpty()) {
            spec = spec.and(InformationSpecifications.tagsContain(filter.getTags()));
        }

        Iterable<Information> informations = informationService.findAll(spec);

        List<InformationResponse> response = new ArrayList<>();

        informations.forEach(information -> response.add(new InformationResponse(information)));

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInformation(@PathVariable Integer id) {
        boolean deleted = informationService.deleteInformationById(id);
        if (deleted) {
            return ResponseEntity.ok("Information with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getInformationById/{id}")
    public ResponseEntity<InformationResponse> getInformationById(@PathVariable Integer id) {
        Information information = informationService.findInformationById(id);
        if (information != null) {
            InformationResponse response = new InformationResponse(information);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
