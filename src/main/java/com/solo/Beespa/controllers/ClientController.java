package com.solo.Beespa.controllers;

import com.solo.Beespa.dtos.request.RegisterBookingRequest;
import com.solo.Beespa.dtos.response.RegisterBookingResponse;
import com.solo.Beespa.service.ClientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientsService clientsService;
    @PostMapping("/registerBooking")
    public ResponseEntity<?> registerBooking(@RequestBody RegisterBookingRequest registerBookingRequest){
        try {
            RegisterBookingResponse response = clientsService.registerBookingRequest(registerBookingRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
