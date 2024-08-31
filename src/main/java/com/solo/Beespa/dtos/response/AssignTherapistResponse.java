package com.solo.Beespa.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignTherapistResponse {
    private String message;
    private String name;
    private Long serviceId;
    private Long therapistId;
}
