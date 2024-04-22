package com.smartflow.smartflow.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "information")
@Getter
@Setter
public class Information {

    @Id
    @Column(name = "informationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer informationId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "uploadDate")
    private Timestamp uploadDate;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Teams team;

    @ElementCollection
    private List<String> tags;

    public Information() {
    }

    public Information(Integer informationId, String name, String description, Timestamp uploadDate, byte[] file,
            String link, Teams team, List<String> tags) {
        this.informationId = informationId;
        this.name = name;
        this.description = description;
        this.uploadDate = uploadDate;
        this.file = file;
        this.link = link;
        this.team = team;
        this.tags = tags;
    }

}
