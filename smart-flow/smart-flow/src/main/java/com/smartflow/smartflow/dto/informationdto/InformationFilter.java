package com.smartflow.smartflow.dto.informationdto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformationFilter {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<Integer> teamId;
    private List<String> tags;
    private List<Integer> typeId;
}
