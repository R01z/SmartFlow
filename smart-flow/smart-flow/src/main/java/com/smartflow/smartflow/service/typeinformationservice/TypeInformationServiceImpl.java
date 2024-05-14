package com.smartflow.smartflow.service.typeinformationservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartflow.smartflow.model.TypeInformation;
import com.smartflow.smartflow.repository.TypeInformationRepository;

public class TypeInformationServiceImpl implements TypeInformationService {

    @Autowired
    private TypeInformationRepository typeInformationRepository;

    @Override
    public List<TypeInformation> getAllTypes() {
        return typeInformationRepository.findAll();
    }

}
