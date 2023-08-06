package com.example.CarRental.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleName;

    private Long model;

    private String brand;

    private String color;

    @ManyToOne
    @JoinColumn(name = "ownerId",referencedColumnName = "id")
    private VehicleOwner ownerId;

//    @OneToMany(mappedBy = "vId")
//    private List<Booking> bookings;
}
