package com.solo.Beespa.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignTherapistRequest {
    private Long serviceId;
    private Long therapistId;
}
