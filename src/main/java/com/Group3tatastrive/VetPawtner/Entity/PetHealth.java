package com.Group3tatastrive.VetPawtner.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long health_id;
    @ManyToOne
    @JoinColumn(name = "petid")
    private Pet pet;
    @Column(name = "checkup_date")
    private LocalDate checkup_date;
    private BigDecimal weight;
    private BigDecimal temperature;
    @Column(name = "notes")
    private String notes;
    @Column(name = "vet_name")
    private String vet_name;

}
