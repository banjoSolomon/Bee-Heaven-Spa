package com.solo.Beespa.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBookingRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String message;
}
