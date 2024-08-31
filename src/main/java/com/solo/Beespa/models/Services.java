package com.solo.Beespa.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ServiceTypes serviceTypes;
    private String description;
    private String duration;
    private BigDecimal price;
    @OneToOne
    private Bookings bookings;
    @ManyToOne
    private Therapist therapist;

}
