package com.solo.Beespa.service;



import com.solo.Beespa.dtos.request.CancelBookingRequest;
import com.solo.Beespa.dtos.request.RegisterBookingRequest;
import com.solo.Beespa.dtos.request.UpdateBookingRequest;
import com.solo.Beespa.dtos.request.ViewAllBookingHistoryRequest;
import com.solo.Beespa.dtos.response.CancelBookingResponse;
import com.solo.Beespa.dtos.response.RegisterBookingResponse;
import com.solo.Beespa.dtos.response.UpdateBookingResponse;
import com.solo.Beespa.dtos.response.ViewAllBookingHistoryResponse;
import com.solo.Beespa.exceptions.BookingNotFoundException;
import com.solo.Beespa.exceptions.InvalidCridentialsException;
import com.solo.Beespa.models.BookingDetails;
import com.solo.Beespa.models.Bookings;
import com.solo.Beespa.repository.BookingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.solo.Beespa.models.BookingState.CANCELLED;


@Service
public class ClientServiceImpl implements ClientsService {
    private final BookingsRepository bookingsRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(BookingsRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingsRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegisterBookingResponse registerBookingRequest(RegisterBookingRequest registerBookingRequest) {
        validation(registerBookingRequest);
        Bookings booking = modelMapper.map(registerBookingRequest, Bookings.class);
        Bookings savedBooking = bookingsRepository.save(booking);
        RegisterBookingResponse response = new RegisterBookingResponse();
        response.setBookingId(savedBooking.getId());
        response.setMessage("Booking successfully registered.");
        return response;

    }

    @Override
    public ViewAllBookingHistoryResponse viewBookingHistory(ViewAllBookingHistoryRequest request) {
        List<BookingDetails> bookingDetailsList = validateFilter(request);
        ViewAllBookingHistoryResponse response = new ViewAllBookingHistoryResponse();
        response.setClientId(request.getClientId());
        response.setBookingHistory(bookingDetailsList);
        return response;
    }

    @Override
    public CancelBookingResponse cancelBooking(CancelBookingRequest request) {
        Bookings bookings = bookingsRepository.findById(request.getBookingId())
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + request.getBookingId()));
        if (!bookings.getClientId().equals(request.getClientId())) {
            throw new InvalidCridentialsException("Client Not Found.");
        }
        bookings.setBookState(CANCELLED);
        bookingsRepository.save(bookings);
        CancelBookingResponse response = new CancelBookingResponse();
        response.setMessage("Booking canceled successfully.");
        response.setStatus("Success");
        response.setCancellationTime(LocalDateTime.now());

        return response;
    }

    @Override
    public UpdateBookingResponse updateBooking(UpdateBookingRequest request) {
        Bookings bookings = checkBooking(request);
        Bookings updatedBooking = bookingsRepository.save(bookings);
        UpdateBookingResponse response = new UpdateBookingResponse();
        response.setMessage("Booking updated successfully.");
        response.setNewTimeAppointment(updatedBooking.getTimeAppointment());
        return response;
    }
    private Bookings checkBooking(UpdateBookingRequest request) {
        Bookings bookings = bookingsRepository.findById(request.getBookingId())
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + request.getBookingId()));

        if (!bookings.getClientId().equals(request.getClientId()))
            throw new InvalidCridentialsException("Client ID does not match the booking owner.");
        if (request.getServiceId() != null)
            bookings.setServiceId(request.getServiceId());
        if (request.getNewTimeAppointment() != null)
            bookings.setTimeAppointment(request.getNewTimeAppointment());
        return bookings;
    }

    private List<BookingDetails> validateFilter(ViewAllBookingHistoryRequest request) {
        return bookingsRepository.findAllByClientId(request.getClientId()).stream()
                .filter(booking -> (request.getServiceType() == null || booking.getServiceTypes() == request.getServiceType()))
                .filter(booking -> (request.getStartDate() == null || !booking.getTimeAppointment().toLocalDate().isBefore(request.getStartDate())))
                .filter(booking -> (request.getEndDate() == null || !booking.getTimeAppointment().toLocalDate().isAfter(request.getEndDate())))
                .map(booking -> {
                    BookingDetails bookingDetails = new BookingDetails();
                    bookingDetails.setId(booking.getId());
                    bookingDetails.setServiceType(booking.getServiceTypes());
                    bookingDetails.setTimeAppointment(booking.getTimeAppointment());
                    bookingDetails.setPrice(booking.getTotalCost());
                    return bookingDetails;
                })
                .collect(Collectors.toList());
    }
    private static void validation(RegisterBookingRequest registerBookingRequest) {
        if(registerBookingRequest.getName() == null|| registerBookingRequest.getName().isEmpty()) {
            throw new InvalidCridentialsException("Name is required");
        }
        if (registerBookingRequest.getEmail() == null || !registerBookingRequest.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new InvalidCridentialsException("Invalid Email");
        }
        if(registerBookingRequest.getPhoneNumber() == null ||!registerBookingRequest.getPhoneNumber().matches("\\d{11}")) {
            throw new InvalidCridentialsException("Invalid Phone Number");
        }
    }
}
