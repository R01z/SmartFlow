package com.smartflow.smartflow.dto.muraldto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MuralRequest {
    private Integer muralId;
    private String title;
    private String text;
    private Integer teamId;
}
