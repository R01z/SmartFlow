package com.smartflow.smartflow.model;

import java.sql.Timestamp;

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
@Table(name = "mural")
@Getter
@Setter
public class Mural {
    @Id
    @Column(name = "muralId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer muralId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "publicationDate")
    private Timestamp publicationDate;

    @ManyToOne
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private Teams team;

    public Mural(Integer muralId, String title, String text, Timestamp publicationDate, Teams team) {
        this.muralId = muralId;
        this.title = title;
        this.text = text;
        this.publicationDate = publicationDate;
        this.team = team;
    }

    public Mural() {
    }
}
