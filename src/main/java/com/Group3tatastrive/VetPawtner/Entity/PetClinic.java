package com.Group3tatastrive.VetPawtner.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PetClinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;

    @Column(name="cname" , length = 30)
    private String cname;

    @Column(name="caddress" , length = 40)
    private String caddress;

    @Column(name="cphonenum" , length = 15)
    private String cphonenum;

    @Column(name="email" , length = 40)
    private String email;

    @Column(name="service" , length = 40)
    private String serviceoffered;

    @Column(name="operatinghrs" , length = 40)
    private int operatinghours;


    @ManyToOne
    private Payment payemt;
}