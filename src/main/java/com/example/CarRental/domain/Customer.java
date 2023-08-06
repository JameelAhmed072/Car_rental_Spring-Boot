package com.example.CarRental.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerCnic;

    private String address;

    private Long customerPhoneNumber;

    @OneToMany(mappedBy = "customerId")
    private List<Booking> bookings;
}
