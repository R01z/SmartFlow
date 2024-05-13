package com.smartflow.smartflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.smartflow.smartflow.model.Mural;
import com.smartflow.smartflow.model.Teams;

@EnableJpaRepositories
@Repository
public interface MuralRepository extends JpaRepository<Mural, Integer> {
    List<Mural> findByTeamTeamId(Integer teamId);
}
