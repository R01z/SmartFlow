package com.smartflow.smartflow.control.informationcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
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
    public Iterable<Information> searchInformations(@RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        Specification<Information> spec = Specification.where(null);

        if (!StringUtils.isEmpty(name)) {
            spec = spec.and(InformationSpecifications.nameContains(name));
        }

        if (!StringUtils.isEmpty(description)) {
            spec = spec.and(InformationSpecifications.descriptionContains(description));
        }

        return informationService.findAll(spec);
    }
}
