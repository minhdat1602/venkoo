package com.nlu.Venko.payload.request;

import com.nlu.Venko.model.enums.FilterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class FilterRequest {
    private FilterStatus status;
    private LocalDate date;
}
