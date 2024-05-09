package com.smartflow.smartflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.model.WikiText;

@EnableJpaRepositories
@Repository
public interface WikiTextRepository extends JpaRepository<WikiText, Integer> {
    WikiText findByTeam(Teams team);
}
