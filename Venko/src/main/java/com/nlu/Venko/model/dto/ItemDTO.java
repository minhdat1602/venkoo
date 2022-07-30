package com.nlu.Venko.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemDTO {
    private Long id;
    private String code;
    private String name;
    private List<TaskDTO> tasks;
}
