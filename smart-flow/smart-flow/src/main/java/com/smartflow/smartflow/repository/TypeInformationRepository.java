package com.smartflow.smartflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartflow.smartflow.model.TypeInformation;

@Repository
public interface TypeInformationRepository extends JpaRepository<TypeInformation, Integer> {

    List<TypeInformation> findAll();
}
