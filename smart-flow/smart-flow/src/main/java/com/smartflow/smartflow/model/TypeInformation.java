package com.smartflow.smartflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "type_information")
@Getter
@Setter
public class TypeInformation {

    @Id
    @Column(name = "type_information_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeInformationId;

    @Column(name = "name")
    private String name;
}
