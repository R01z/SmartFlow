package com.smartflow.smartflow.dto.teamdto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {

    private Integer teamId;

    private String name;

    private String description;

    private List<String> membersEmails = new ArrayList<>();

    public TeamDTO() {
    }

    public TeamDTO(Integer teamId, String name, String description, List<String> membersEmail) {
        this.teamId = teamId;
        this.name = name;
        this.description = description;
        this.membersEmails = membersEmail;
    }

}
