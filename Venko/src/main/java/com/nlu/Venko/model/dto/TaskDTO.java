package com.nlu.Venko.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private Boolean isDone;
    private LocalDate date;
    private Long itemId;
}
