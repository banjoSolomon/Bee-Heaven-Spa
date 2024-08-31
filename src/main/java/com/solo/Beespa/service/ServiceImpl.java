package com.solo.Beespa.service;

import com.solo.Beespa.dtos.request.AssignTherapistRequest;
import com.solo.Beespa.dtos.request.UpdateServicePriceRequest;
import com.solo.Beespa.dtos.response.AssignTherapistResponse;
import com.solo.Beespa.dtos.response.UpdatePriceResponse;
import com.solo.Beespa.exceptions.InvalidPriceException;
import com.solo.Beespa.exceptions.ServiceNotFoundException;
import com.solo.Beespa.exceptions.TherapistNotFoundException;
import com.solo.Beespa.models.Services;
import com.solo.Beespa.repository.ServiceRepository;
import com.solo.Beespa.repository.TherapistRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final TherapistRepository therapistRepository;

    public ServiceImpl(ServiceRepository serviceRepository, TherapistRepository therapistRepository) {
        this.serviceRepository = serviceRepository;
        this.therapistRepository = therapistRepository;
    }

    @Override
    public UpdatePriceResponse updatePrice(UpdateServicePriceRequest request) {
        Services service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ServiceNotFoundException("Service not found with ID: " + request.getServiceId()));
        if(request.getNewPrice().compareTo(BigDecimal.ZERO) < 0) throw new InvalidPriceException("Price cannot be negative");
        service.setPrice(request.getNewPrice());
        serviceRepository.save(service);
        UpdatePriceResponse response = new UpdatePriceResponse();
        response.setNewPrice(service.getPrice());
        response.setMessage("Service price updated successfully.");
        return response;
    }

    @Override
    public AssignTherapistResponse assignTherapist(AssignTherapistRequest request) {
        Services service = serviceRepository.findById(request.getServiceId())
               .orElseThrow(() -> new ServiceNotFoundException("Service not found with ID: " + request.getServiceId()));
        service.setTherapist(therapistRepository.findById(request.getTherapistId())
               .orElseThrow(() -> new TherapistNotFoundException("Therapist not found with ID: " + request.getTherapistId())));
        serviceRepository.save(service);
        AssignTherapistResponse response = new AssignTherapistResponse();
        response.setName(service.getTherapist().getName());
        response.setServiceId(service.getTherapist().getId());
        response.setTherapistId(service.getTherapist().getId());
        response.setMessage("Therapist assigned successfully.");
        return response;


    }
}