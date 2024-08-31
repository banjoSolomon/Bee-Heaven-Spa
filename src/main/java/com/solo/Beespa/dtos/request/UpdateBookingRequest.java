package com.solo.Beespa.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateBookingRequest {
    private Long bookingId;
    private Long clientId;
    private Long serviceId;
    private Long therapistId;
    private LocalDateTime newTimeAppointment;
    private String message;
}
