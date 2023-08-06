package com.example.CarRental.dto;

import lombok.*;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingDTO {


    private Long id;

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Vehicle ID cannot be null")
    private Long vehicleId;

    @NotNull(message = "Booking date cannot be null")
    private LocalDate bookingDate;

    private LocalDate completedDate;

    private Double price;

    private String bookingStatus;



}
