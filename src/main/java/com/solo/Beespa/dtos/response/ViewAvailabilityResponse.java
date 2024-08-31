package com.solo.Beespa.dtos.response;

import com.solo.Beespa.models.ServiceTypes;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ViewAvailabilityResponse {
    private Long id;
    private String name;
    private ServiceTypes serviceType;
    private String availability;
}
