package com.smartflow.smartflow.service.typeinformationservice;

import java.util.List;

import com.smartflow.smartflow.model.TypeInformation;

public interface TypeInformationService {
    List<TypeInformation> getAllTypeInformations();

    TypeInformation findTypeInformationById(Integer id);
}
