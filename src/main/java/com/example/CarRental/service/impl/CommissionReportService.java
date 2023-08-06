package com.example.CarRental.service.impl;

import com.example.CarRental.dto.CommissionReportDTO;
import com.example.CarRental.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommissionReportService {

    private final BookingRepository bookingRepository;

    public CommissionReportService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<CommissionReportDTO> generateCommissionReport(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.generateCommissionReport(startDate, endDate);
    }
}
