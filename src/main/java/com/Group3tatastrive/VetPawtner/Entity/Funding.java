package com.Group3tatastrive.VetPawtner.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Funding")
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int funding_id;
    @Column(name = "Amount")
    private double amount;
    @Column(name = "Funding Type")
    private String funding_type;
    @Column(name = "Purpose")
    private String purpose;
    @Column(name = "Date Received")
    private  String date_received;
    @Column(name = "Status")
    private String Status;
    @ManyToOne
    private User user;
}
