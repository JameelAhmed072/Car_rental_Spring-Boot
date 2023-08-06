package com.example.CarRental.service.impl;

import com.example.CarRental.domain.Booking;
import com.example.CarRental.domain.Customer;
import com.example.CarRental.domain.Vehicle;
import com.example.CarRental.dto.BookingDTO;
import com.example.CarRental.dto.MonthlyReportDTO;
import com.example.CarRental.exception.ResourceNotFoundException;
import com.example.CarRental.repository.BookingRepository;
import com.example.CarRental.repository.CustomerRepository;
import com.example.CarRental.repository.VehicleRepository;
import com.example.CarRental.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + bookingDTO.getCustomerId()));

        Vehicle vehicle = vehicleRepository.findById(bookingDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + bookingDTO.getVehicleId()));

        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setCustomerId(customer);
        booking.setVehicleId(vehicle);

        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }
    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }
//    @Override
//    public List<BookingDTO> getmonthlyData() {
//        List<BookingDTO> bookings = bookingRepository.findAllBookingData();
//        return bookings.stream()
//                .map(booking -> modelMapper.map(booking, BookingDTO.class))
//                .collect(Collectors.toList());
//    }
    @Override
    public BookingDTO getBookingById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        Booking booking = optionalBooking.orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));

        Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + bookingDTO.getCustomerId()));

        Vehicle vehicle = vehicleRepository.findById(bookingDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + bookingDTO.getVehicleId()));

        booking.setCustomerId(customer);
        booking.setVehicleId(vehicle);
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setCompletedDate(bookingDTO.getCompletedDate());
        booking.setPrice(bookingDTO.getPrice());
        booking.setBookingStatus(bookingDTO.getBookingStatus());

        Booking updatedBooking = bookingRepository.save(booking);
        return modelMapper.map(updatedBooking, BookingDTO.class);
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }

//    @Override
//    public List<Booking> getBookingByCustomerId(Long customerId) {
//        return bookingRepository.getBookingByCustomerId(customerId);
//    }


//    @Override
//    public List<BookingDTO> findAllBookingDataByDateRange(LocalDate startDate, LocalDate endDate) {
//        return bookingRepository.findAllBookingDataByDateRange(startDate, endDate);
//    }

//    @Override
//    public List<MonthlyReportDTO> getMonthlyReport(LocalDate startDate, LocalDate endDate) {
//        return bookingRepository.findMonthlyReportByDateRange(startDate, endDate);
//    }

//    public List<BookingDTO> getMonRepo(LocalDate startDate, LocalDate endDate){
//        return bookingRepository.findMonthlyReportByDateRange(startDate,endDate);
//    }



//    public List<BookingDTO> monthlyReport(java.sql.Date startDate, Date endDate){
//        return bookingRepository.getCustomBookingDataByDateRange(startDate,endDate);
//    }


}
