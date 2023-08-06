package com.example.CarRental.controller;

import com.example.CarRental.dto.BookingDTO;
import com.example.CarRental.service.BookingService;
import com.example.CarRental.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingServiceImpl bookingserviceImp;

    @PostMapping("/booking")
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping("/allbookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
//    @GetMapping("/monthlyreport")
//    public ResponseEntity<List<BookingDTO>> getmonthlyBookings() {
//        List<BookingDTO> bookings = bookingService.getmonthlyData();
//        return ResponseEntity.ok(bookings);
//    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@Valid @PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }
    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
//    @GetMapping("/bookingdata")
//    public ResponseEntity<List<BookingDTO>> getAllBookingDataByDateRange(@RequestParam("start") String startDate,
//                                                                         @RequestParam("end") String endDate) {
//        LocalDate start = LocalDate.parse(startDate);
//        LocalDate end = LocalDate.parse(endDate);
//        List<BookingDTO> bookings = bookingService.findAllBookingDataByDateRange(start, end);
//        return ResponseEntity.ok(bookings);
//    }


//    @GetMapping("/monthlyreport")
//    public ResponseEntity<List<BookingDTO>> monthlyReports(@RequestParam("start") String startDate,@RequestParam("end") String endDate){
//        LocalDate start = LocalDate.parse(startDate);
//        LocalDate end = LocalDate.parse(endDate);
//        List<BookingDTO> bookings = bookingserviceImp.getMonRepo(start, end);
//        return ResponseEntity.ok(bookings);
//    }


//    @GetMapping("/getbookingbycustomerid/{customerId}")
//    public ResponseEntity<List<Booking>> getByCustomerId(@PathVariable Long customerId){
//        List<Booking> bookings = bookingService.getBookingByCustomerId(customerId);
//        return ResponseEntity.ok(bookings);
//    }





}
