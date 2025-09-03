package com.Group3tatastrive.VetPawtner.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal; // For handling money
import java.time.LocalDate;  // For the date
import java.time.LocalTime;  // For the time

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @ManyToOne
    private User user;


    @ManyToOne
    private Pet pet;

    @ManyToOne
    private PetClinic petClinic;

    private String doctorName;

    private LocalDate appointmentDate;


    @Column(name = "appointment_time")
    private LocalTime appointmentTime;
    @ManyToOne
    private Payment payment;

    private String status; // e.g., "Pending", "Confirmed"

}