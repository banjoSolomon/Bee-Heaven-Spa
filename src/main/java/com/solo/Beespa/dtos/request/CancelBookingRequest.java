package com.solo.Beespa.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelBookingRequest {
    private Long bookingId;
    private Long clientId;
    private String reason;
}
