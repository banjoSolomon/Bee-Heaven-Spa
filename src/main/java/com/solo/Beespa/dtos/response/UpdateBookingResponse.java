package com.solo.Beespa.dtos.response;

import com.solo.Beespa.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateBookingResponse {
    private Long bookingId;
    private PaymentStatus status;
    private String message;
    private LocalDateTime newTimeAppointment;
}
