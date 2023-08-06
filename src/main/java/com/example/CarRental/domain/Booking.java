package com.example.CarRental.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "vehicleId", referencedColumnName = "id")
    private Vehicle vehicleId;

    private LocalDate bookingDate;

    private LocalDate completedDate;

    private Double price;

    private String bookingStatus;

}
