package com.smartflow.smartflow.service.informationservice;

import org.springframework.data.jpa.domain.Specification;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
import com.smartflow.smartflow.model.Information;

public interface InformationService {

    Iterable<Information> findAll(Specification<Information> spec);

    String addInformation(InformationDTO informationDTO);

    public boolean deleteInformationById(Integer id);

    Information findInformationById(Integer id);
}
