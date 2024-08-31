package com.solo.Beespa.dtos.response;

import com.solo.Beespa.models.BookingDetails;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class ViewAllBookingHistoryResponse {
    private Long clientId;
    private List<BookingDetails> bookingHistory;

}
