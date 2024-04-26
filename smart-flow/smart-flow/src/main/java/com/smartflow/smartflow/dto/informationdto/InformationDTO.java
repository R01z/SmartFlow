package com.smartflow.smartflow.dto.informationdto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class InformationDTO {

    private Integer informationId;

    private String name;

    private String description;

    private String link;

    private byte[] file;

    private Integer teamId;

    private List<String> tags;
}
