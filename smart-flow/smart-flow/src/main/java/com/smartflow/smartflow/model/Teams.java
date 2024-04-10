package com.smartflow.smartflow.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Teams {

    @Id
    @Column(name = "teamId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "teamId", referencedColumnName = "teamId"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
    // @JsonIgnore
    private List<User> members = new ArrayList<>();

    @Column(name = "description")
    private String description;

    public Teams() {
    }

    public Teams(Integer teamId, String name, List<User> members, String description) {
        this.teamId = teamId;
        this.name = name;
        this.members = members;
        this.description = description;
    }

    public void addMembers(List<User> newMembers) {
        List<User> oldMembers = getMembers();

        for (User user : newMembers) {
            oldMembers.add(user);
        }

        setMembers(oldMembers);
    }
}
