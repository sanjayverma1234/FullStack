package com.Group3tatastrive.VetPawtner.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    @Column(name = "Amount")
    private double amount;
    @Column(name = "Status")
    private String status;
    @Column(name = "Date")
    private String date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Funding funding;
}
