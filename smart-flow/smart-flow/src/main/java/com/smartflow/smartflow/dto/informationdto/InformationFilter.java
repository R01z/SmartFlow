package com.smartflow.smartflow.dto.informationdto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformationFilter {
    private String name;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<Integer> teamId;
    private List<String> tags;
}
