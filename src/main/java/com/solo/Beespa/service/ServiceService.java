package com.solo.Beespa.service;


import com.solo.Beespa.dtos.request.AssignTherapistRequest;
import com.solo.Beespa.dtos.request.UpdateServicePriceRequest;
import com.solo.Beespa.dtos.response.AssignTherapistResponse;
import com.solo.Beespa.dtos.response.UpdatePriceResponse;

public interface ServiceService {
    UpdatePriceResponse updatePrice(UpdateServicePriceRequest request);

    AssignTherapistResponse assignTherapist(AssignTherapistRequest request);

}
