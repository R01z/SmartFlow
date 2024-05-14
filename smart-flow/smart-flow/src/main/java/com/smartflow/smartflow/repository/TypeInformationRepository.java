package com.smartflow.smartflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.smartflow.smartflow.model.TypeInformation;

@EnableJpaRepositories
@Repository
public interface TypeInformationRepository {

    List<TypeInformation> findAll();

    TypeInformation getReferenceById(Integer typeId);
}
