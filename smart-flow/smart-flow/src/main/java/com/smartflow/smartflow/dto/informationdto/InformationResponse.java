package com.smartflow.smartflow.dto.informationdto;

import java.sql.Timestamp;
import java.util.List;

import com.smartflow.smartflow.model.Information;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformationResponse {
    private Integer informationId;
    private String name;
    private String description;
    private Timestamp uploadDate;
    private byte[] file;
    private String link;
    private Integer teamId;
    private List<String> tags;

    public InformationResponse(Information information) {
        this.informationId = information.getInformationId();
        this.name = information.getName();
        this.description = information.getDescription();
        this.uploadDate = information.getUploadDate();
        this.file = information.getFile();
        this.link = information.getLink();
        this.teamId = information.getTeam().getTeamId();
        this.tags = information.getTags();
    }
}
