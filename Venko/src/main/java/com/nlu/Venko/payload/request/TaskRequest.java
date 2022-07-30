package com.nlu.Venko.payload.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String title;
    private Boolean isDone;
    private LocalDate date;
    private Long itemId;
}
