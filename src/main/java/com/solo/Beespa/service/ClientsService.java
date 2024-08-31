package com.solo.Beespa.service;


import com.solo.Beespa.dtos.request.CancelBookingRequest;
import com.solo.Beespa.dtos.request.RegisterBookingRequest;
import com.solo.Beespa.dtos.request.UpdateBookingRequest;
import com.solo.Beespa.dtos.request.ViewAllBookingHistoryRequest;
import com.solo.Beespa.dtos.response.CancelBookingResponse;
import com.solo.Beespa.dtos.response.RegisterBookingResponse;
import com.solo.Beespa.dtos.response.UpdateBookingResponse;
import com.solo.Beespa.dtos.response.ViewAllBookingHistoryResponse;

public interface ClientsService {

    RegisterBookingResponse registerBookingRequest(RegisterBookingRequest registerBookingRequest);

    ViewAllBookingHistoryResponse viewBookingHistory(ViewAllBookingHistoryRequest request);
    CancelBookingResponse cancelBooking(CancelBookingRequest request);

    UpdateBookingResponse updateBooking(UpdateBookingRequest request);

}
