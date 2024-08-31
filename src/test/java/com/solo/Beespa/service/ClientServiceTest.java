package com.solo.Beespa.service;

import com.solo.Beespa.dtos.request.CancelBookingRequest;
import com.solo.Beespa.dtos.request.RegisterBookingRequest;
import com.solo.Beespa.dtos.request.UpdateBookingRequest;
import com.solo.Beespa.dtos.request.ViewAllBookingHistoryRequest;
import com.solo.Beespa.dtos.response.CancelBookingResponse;
import com.solo.Beespa.dtos.response.RegisterBookingResponse;
import com.solo.Beespa.dtos.response.UpdateBookingResponse;
import com.solo.Beespa.dtos.response.ViewAllBookingHistoryResponse;
import com.solo.Beespa.exceptions.InvalidCridentialsException;
import com.solo.Beespa.models.ServiceTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class ClientServiceTest {
    @Autowired
    private ClientsService clientService;

    @Test
    public void testClientCanRegisterABooking() {
        RegisterBookingRequest registerBookingRequest = new RegisterBookingRequest();
        registerBookingRequest.setName("name");
        registerBookingRequest.setEmail("ayomidebanjo02@gmail.com");
        registerBookingRequest.setPhoneNumber("08012345671");
        registerBookingRequest.setMessage("coming for massage");
        RegisterBookingResponse response = clientService.registerBookingRequest(registerBookingRequest);
        assertNotNull(response);
        assertEquals("Booking successfully registered.", response.getMessage());

    }

    @Test
    public void testClientCanViewBookingHistory() {
        ViewAllBookingHistoryRequest request = new ViewAllBookingHistoryRequest();
        request.setClientId(2L);
        request.setStartDate(LocalDate.of(2024, 1, 1));
        request.setEndDate(LocalDate.of(2024, 12, 31));

        ViewAllBookingHistoryResponse response = clientService.viewBookingHistory(request);
        assertNotNull(response);
        assertEquals(2L, response.getClientId());
        assertEquals(2, response.getBookingHistory().size());
        assertTrue(response.getBookingHistory().stream()
                .anyMatch(b -> b.getServiceType() == ServiceTypes.MASSAGE && b.getTimeAppointment().toLocalDate().equals(LocalDate.of(2024, 6, 10)))); // Verify first booking
        assertTrue(response.getBookingHistory().stream()
                .anyMatch(b -> b.getServiceType() == ServiceTypes.FACIALS && b.getTimeAppointment().toLocalDate().equals(LocalDate.of(2024, 6, 12))));


    }

    @Test
    public void testClientCannotRegisterBookingWithInvalidData() {
        RegisterBookingRequest registerBookingRequest = new RegisterBookingRequest();
        registerBookingRequest.setEmail("invalid-mail");
        registerBookingRequest.setName("");
        registerBookingRequest.setPhoneNumber("8999333");
        assertThrows(InvalidCridentialsException.class, () -> {
            clientService.registerBookingRequest(registerBookingRequest);
        });

    }

    @Test
    public void testClientCanRegisterBookingWithoutOptionalFields() {
        RegisterBookingRequest registerBookingRequest = new RegisterBookingRequest();
        registerBookingRequest.setName("name");
        registerBookingRequest.setEmail("email@email.com");
        registerBookingRequest.setPhoneNumber("08012345678");

        RegisterBookingResponse response = clientService.registerBookingRequest(registerBookingRequest);

        assertNotNull(response);
        assertEquals("Booking successfully registered.", response.getMessage());
    }

    @Test
    public void testClientCanUpdateBookingInformation() {
        UpdateBookingRequest request = new UpdateBookingRequest();
        request.setClientId(2L);
        request.setBookingId(12L);
        request.setMessage("Booking updated successfully");
        request.setNewTimeAppointment(LocalDateTime.of(2024, 12, 11, 14, 0));
        UpdateBookingResponse response = clientService.updateBooking(request);
        assertNotNull(response);
        assertEquals("Booking updated successfully.", response.getMessage());
        assertEquals(response.getNewTimeAppointment().toLocalDate(), LocalDate.of(2024, 12, 11));


    }

    @Test
    public void testClientCanCancelBooking() {
        CancelBookingRequest request = new CancelBookingRequest();
        request.setClientId(2L);
        request.setBookingId(12L);
        request.setReason("Not Available");
        CancelBookingResponse response = clientService.cancelBooking(request);
        assertNotNull(response);
        assertNotNull(response);
        assertEquals("Booking canceled successfully.", response.getMessage());
        assertEquals("Success", response.getStatus());
        assertNotNull(response.getCancellationTime());
        assertTrue(response.getCancellationTime().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}



