package com.smartflow.smartflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.smartflow.smartflow.model.Roles;

@EnableJpaRepositories
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    List<Roles> findAll();
}
