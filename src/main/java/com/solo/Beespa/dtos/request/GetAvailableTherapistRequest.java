package com.solo.Beespa.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetAvailableTherapistRequest {
    private LocalDate requestedDate;
    private Long serviceId;
}
