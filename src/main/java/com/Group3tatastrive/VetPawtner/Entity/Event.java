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
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;
    @Column(name = "Name")
    private String event_name;
    @Column(name = "Price")
    private Double Price;
    @Column(name = "Location")
    private String location;
    @Column(name = "Time")
    private String time;
    @Column(name = "Date")
    private String date;
    @ManyToOne
    private User user;


}
