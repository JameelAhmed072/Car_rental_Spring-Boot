package com.example.CarRental.service.impl;

import com.example.CarRental.dto.MonthlyReportDTO;
import com.example.CarRental.repository.BookingRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Service
public class MonthlyReportService {
    private final BookingRepository bookingRepository;
    public MonthlyReportService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<MonthlyReportDTO> generateMonthlyReport(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.generateMonthlyReport(startDate, endDate);
    }

}
