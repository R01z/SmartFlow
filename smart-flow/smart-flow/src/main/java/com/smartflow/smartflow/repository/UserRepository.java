package com.smartflow.smartflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.smartflow.smartflow.model.User;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    Optional<User> findOneByEmailAndPassword(String email, String password);

    List<User> findAll();
}
