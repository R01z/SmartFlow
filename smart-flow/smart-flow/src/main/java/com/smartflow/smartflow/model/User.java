package com.smartflow.smartflow.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    private String email;

    private String password;

    private Timestamp registrationDate;

    public User() {
    }

    public User(Integer userId, String name, String email, String password, Timestamp registrationDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

}
