package com.smartflow.smartflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wikitext")
@Getter
@Setter
public class WikiText {

    @Id
    @Column(name = "wikiId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wikiId;

    @Column(name = "text", length = 10000)
    private String text;

    @ManyToOne
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private Teams team;
}
