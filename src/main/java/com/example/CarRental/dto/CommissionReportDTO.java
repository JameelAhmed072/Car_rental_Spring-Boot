package com.example.CarRental.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommissionReportDTO {

    private String ownerName;
    private String vehicleName;
    private Double price;
    private LocalDate bookingDate;
    private LocalDate completedDate;
    private double totalPrice;
    private double commission;
    private double totalCommission;
}
