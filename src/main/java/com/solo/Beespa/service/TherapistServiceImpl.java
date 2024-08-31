package com.solo.Beespa.service;

import com.solo.Beespa.dtos.request.GetAvailableTherapistRequest;
import com.solo.Beespa.dtos.request.ViewAvailabilityAvailabilityRequest;
import com.solo.Beespa.dtos.response.AvailabilityResponse;
import com.solo.Beespa.dtos.response.ViewAvailabilityResponse;
import com.solo.Beespa.exceptions.TherapistNotFoundException;
import com.solo.Beespa.models.Therapist;
import com.solo.Beespa.repository.TherapistRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TherapistServiceImpl implements TherapistService {
   public final TherapistRepository therapistRepository;

    public TherapistServiceImpl(TherapistRepository therapistRepository) {
        this.therapistRepository = therapistRepository;
    }

    @Override
    public AvailabilityResponse viewAvailability(ViewAvailabilityAvailabilityRequest request) {
        Optional<Therapist> therapist = therapistRepository.findById(request.getTherapistId());
        if (therapist.isEmpty()) {
            throw new TherapistNotFoundException("Therapist not found with ID: " + request.getTherapistId());

        }
        AvailabilityResponse availabilityResponse = new AvailabilityResponse();
        availabilityResponse.setTherapistId(therapist.get().getId());
        availabilityResponse.setTherapistName(therapist.get().getName());
        List<String> availabilitySlot = new ArrayList<>();
        availabilitySlot.add("09:00 AM - 10:00 AM");
        availabilitySlot.add("10:00 AM - 11:00 AM");
        availabilityResponse.setAvailabilitySlots(availabilitySlot);
        return availabilityResponse;
    }

    @Override
    public List<ViewAvailabilityResponse> getAvailableTherapists(GetAvailableTherapistRequest request) {
        List<Therapist> therapists = therapistRepository.findAll();
        return therapists.stream()
                .map(therapist -> {
                    ViewAvailabilityResponse viewAvailabilityResponse = new ViewAvailabilityResponse();
                    viewAvailabilityResponse.setId(therapist.getId());
                    viewAvailabilityResponse.setName(therapist.getName());
                    viewAvailabilityResponse.setAvailability(therapist.getAvailabilitySchedule());
                    return viewAvailabilityResponse;
                })
                .sorted(Comparator.comparing(ViewAvailabilityResponse::getName))
                .collect(Collectors.toList());

    }
}
