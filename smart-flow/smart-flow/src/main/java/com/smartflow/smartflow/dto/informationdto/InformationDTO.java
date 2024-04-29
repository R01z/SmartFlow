package com.smartflow.smartflow.dto.informationdto;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile file;

    private Integer teamId;

    private List<String> tags;
}
