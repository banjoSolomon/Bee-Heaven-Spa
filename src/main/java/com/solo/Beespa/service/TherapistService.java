package com.solo.Beespa.service;


import com.solo.Beespa.dtos.request.GetAvailableTherapistRequest;
import com.solo.Beespa.dtos.request.ViewAvailabilityAvailabilityRequest;
import com.solo.Beespa.dtos.response.AvailabilityResponse;
import com.solo.Beespa.dtos.response.ViewAvailabilityResponse;

import java.util.List;

public interface TherapistService {
    AvailabilityResponse viewAvailability(ViewAvailabilityAvailabilityRequest request);

    List<ViewAvailabilityResponse> getAvailableTherapists(GetAvailableTherapistRequest request);

}
