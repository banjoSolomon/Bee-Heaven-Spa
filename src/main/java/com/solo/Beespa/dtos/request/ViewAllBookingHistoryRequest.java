package com.solo.Beespa.dtos.request;

import com.solo.Beespa.models.ServiceTypes;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class ViewAllBookingHistoryRequest {
    private Long clientId;
    private Long bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ServiceTypes serviceType;

}
