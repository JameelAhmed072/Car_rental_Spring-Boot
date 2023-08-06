package com.example.CarRental.service;


import com.example.CarRental.domain.Booking;
import com.example.CarRental.dto.BookingDTO;
import com.example.CarRental.dto.MonthlyReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);


    List<BookingDTO> getAllBookings();


    BookingDTO getBookingById(Long id);


    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);


    void deleteBooking(Long id);

//    public List<BookingDTO> getmonthlyData();

//    List<BookingDTO> findAllBookingDataByDateRange(LocalDate startDate, LocalDate endDate);


//    List<MonthlyReportDTO> getMonthlyReport(LocalDate startDate, LocalDate endDate);


//    List<Booking> getBookingByCustomerId(Long customerId);

}
