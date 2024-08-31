package com.solo.Beespa.dtos.request;

import com.solo.Beespa.models.ServiceTypes;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter
public class ViewAvailabilityAvailabilityRequest {
    private Long therapistId;
    private LocalDate requestedDate;
    private ServiceTypes serviceTypes;
}
