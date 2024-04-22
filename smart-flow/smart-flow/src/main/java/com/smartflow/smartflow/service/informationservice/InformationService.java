package com.smartflow.smartflow.service.informationservice;

import org.springframework.data.jpa.domain.Specification;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
import com.smartflow.smartflow.model.Information;

public interface InformationService {

    Iterable<Information> findAll(Specification<Information> spec);

    String addTeam(InformationDTO informationDTO);

}
