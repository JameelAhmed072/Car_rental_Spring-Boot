package com.example.CarRental.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyReportDTO {
    private Long id;
    private String customerName;
    private String vehicleName;
    private LocalDate bookingDate;
    private LocalDate completedDate;
    private Double price;
    private Double commission;
    private String bookingStatus;
}
