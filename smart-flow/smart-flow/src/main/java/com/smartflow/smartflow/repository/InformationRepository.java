package com.smartflow.smartflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.smartflow.smartflow.model.Information;

@EnableJpaRepositories
@Repository
public interface InformationRepository
        extends JpaRepository<Information, Integer>, JpaSpecificationExecutor<Information> {

}
