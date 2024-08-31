package com.solo.Beespa.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailabilityResponse {
    private Long therapistId;
    private String therapistName;
    private List<String> availabilitySlots;
}
