package com.smartflow.smartflow.control.informationcontroller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
import com.smartflow.smartflow.dto.informationdto.InformationFilter;
import com.smartflow.smartflow.dto.informationdto.InformationResponse;
import com.smartflow.smartflow.model.Information;
import com.smartflow.smartflow.service.informationservice.InformationService;
import com.smartflow.smartflow.specifications.InformationSpecifications;

@RestController
@CrossOrigin
@RequestMapping("api/v1/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @PostMapping(path = "/save")
    public String saveUsers(@RequestBody InformationDTO informationDTO) {
        return informationService.addInformation(informationDTO);
    }

    @GetMapping("/getInformations")
    public List<InformationResponse> searchInformations(@RequestBody InformationFilter filter) {
        Specification<Information> spec = Specification.where(null);

        if (StringUtils.hasText(filter.getName())) {
            spec = spec.and(InformationSpecifications.nameContains(filter.getName()));
        }

        if (StringUtils.hasText(filter.getDescription())) {
            spec = spec.and(InformationSpecifications.descriptionContains(filter.getDescription()));
        }

        if (filter.getStartDate() != null) {
            Timestamp endDate = filter.getEndDate() != null ? filter.getEndDate()
                    : new Timestamp(System.currentTimeMillis());

            spec = spec.and(InformationSpecifications.uploadDateBetween(filter.getStartDate(), endDate));
        }

        Iterable<Information> informations = informationService.findAll(spec);

        List<InformationResponse> response = new ArrayList<>();

        informations.forEach(information -> response.add(new InformationResponse(information)));

        return response;
    }
}
