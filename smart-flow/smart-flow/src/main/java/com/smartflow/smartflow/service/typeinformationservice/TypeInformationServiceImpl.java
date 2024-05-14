package com.smartflow.smartflow.service.typeinformationservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.smartflow.model.TypeInformation;
import com.smartflow.smartflow.repository.TypeInformationRepository;

@Service
public class TypeInformationServiceImpl implements TypeInformationService {

    @Autowired
    private TypeInformationRepository typeInformationRepository;

    @Override
    public List<TypeInformation> getAllTypeInformations() {
        return typeInformationRepository.findAll();
    }

    @Override
    public TypeInformation findTypeInformationById(Integer id) {
        return typeInformationRepository.getReferenceById(id);
    }

}
