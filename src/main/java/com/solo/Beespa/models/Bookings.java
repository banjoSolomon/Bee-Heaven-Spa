package com.solo.Beespa.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long clientId;
    private Long serviceId;
    @OneToMany(mappedBy = "bookings", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDetails> bookingList;
    @Enumerated(EnumType.STRING)
    private ServiceTypes serviceTypes;
    @Enumerated(EnumType.STRING)
    private BookingState bookState;
    @OneToOne
    private Therapist therapist;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeAppointment;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private BigDecimal totalCost;





}
