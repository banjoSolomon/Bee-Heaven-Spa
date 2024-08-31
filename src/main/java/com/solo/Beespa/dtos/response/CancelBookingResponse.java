package com.solo.Beespa.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CancelBookingResponse {
    private String message;
    private String status;
    private LocalDateTime cancellationTime;
}
