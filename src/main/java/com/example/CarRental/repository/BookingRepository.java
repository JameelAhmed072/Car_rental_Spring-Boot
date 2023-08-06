package com.example.CarRental.repository;

import com.example.CarRental.domain.Booking;
import com.example.CarRental.dto.CommissionReportDTO;
import com.example.CarRental.dto.MonthlyReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {



//    @Query("SELECT new com.example.CarRental.dto." +
//            "BookingDTO(b.id, b.customerId.id, b.vehicleId.id, " +
//            "b.bookingDate, b.completedDate, b.price, b.bookingStatus," +
//            " b.customerId.customerName, b.vehicleId.vehicleName) FROM Booking b")
//    List<BookingDTO> findAllBookingData();

//

//    List<Booking> findByBookingDateBetween(LocalDate startDate, LocalDate endDate);

//    @Query("SELECT NEW com.example.CarRental.dto.MonthlyReportDTO(" +
//            "b.id, c.customerName, v.vehicleName, b.bookingDate, b.completedDate, " +
//            "(b.price * (EXTRACT(DAY FROM b.completedDate) - EXTRACT(DAY FROM b.bookingDate))), " +
//            "o.commission, b.bookingStatus) " +
//            "FROM Booking b " +
//            "JOIN b.customer c " +
//            "JOIN b.vehicle v " +
//            "JOIN v.owner o " +
//            "WHERE b.bookingDate BETWEEN :startDate AND :endDate")
//    List<MonthlyReportDTO> findMonthlyReportByDateRange(LocalDate startDate, LocalDate endDate);


//    @Query(value = "SELECT b.id, c.customer_name, v.vehicle_name, b.booking_date, b.completed_date, " +
//            "(b.price * DATEDIFF(b.completed_date, b.booking_date)) AS price, o.commission, " +
//            "b.booking_status " +
//            "FROM booking b " +
//            "INNER JOIN customer c ON b.customer_id = c.id " +
//            "INNER JOIN vehicle v ON b.vehicle_id = v.id " +
//            "INNER JOIN vehicle_owner o ON b.vehicle_id = o.id " +
//            "WHERE b.booking_date BETWEEN ?1 AND ?2", nativeQuery = true)
//    List<BookingDTO> findMonthlyReportByDateRange(LocalDate startDate, LocalDate endDate);

//    @Query("select b from booking b where b.customerId = :n")
//    public List<Booking> getBookingByCustomerId(@Param("n") Long customerId);

//    @Query("SELECT b.id, c.customerName, v.vehicleName, b.bookingDate, b.completedDate, " +
//            "(b.price * DATEDIFF(b.completedDate, b.bookingDate)) AS price, o.commission, " +
//            "b.bookingStatus FROM Booking b " +
//            "INNER JOIN Customer c ON b.customerId = c.id " +
//            "INNER JOIN Vehicle v ON b.vehicleId = v.id " +
//            "INNER JOIN VehicleOwner o ON b.vehicleId = o.id " +
//            "WHERE b.bookingDate BETWEEN :startDate AND :endDate")
//    List<BookingDTO> getCustomBookingDataByDateRange(Date startDate, Date endDate);

    @Query("SELECT new com.example.CarRental.dto.MonthlyReportDTO(b.id, c.customerName, v.vehicleName, " +
            "b.bookingDate, b.completedDate, (b.price * FUNCTION('DATEDIFF', b.completedDate, b.bookingDate)), " +
            "o.commission, b.bookingStatus) " +
            "FROM Booking b " +
            "INNER JOIN b.customerId c " +
            "INNER JOIN b.vehicleId v " +
            "INNER JOIN v.ownerId o " +
            "WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    List<MonthlyReportDTO> generateMonthlyReport(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);
//    @Query("SELECT new com.example.CarRental.dto.CommissionReportDTO(o.ownerName, " +
//            "SUM(o.commission * FUNCTION('DATEDIFF', b.completedDate, b.bookingDate) * b.price / 100)) " +
//            "FROM Booking b " +
//            "INNER JOIN b.vehicleId v " +
//            "INNER JOIN v.ownerId o " +
//            "WHERE b.completedDate BETWEEN :startDate AND :endDate " +
//            "GROUP BY o.ownerName")
//    List<CommissionReportDTO> generateCommissionReport(@Param("startDate") LocalDate startDate,
//                                                       @Param("endDate") LocalDate endDate);


    @Query("SELECT new com.example.CarRental.dto.CommissionReportDTO(o.ownerName,v.vehicleName,b.price, " +
            "b.bookingDate,b.completedDate,SUM(b.price * FUNCTION('DATEDIFF', b.completedDate, b.bookingDate)), " +
            "o.commission,SUM((o.commission * FUNCTION('DATEDIFF', b.completedDate, b.bookingDate) * b.price) / 100)) " +
            "FROM Booking b JOIN b.vehicleId v JOIN v.ownerId o WHERE b.completedDate BETWEEN :startDate AND :endDate " +
            "GROUP BY o.ownerName, v.vehicleName, b.price, b.bookingDate, b.completedDate, o.commission")
    List<CommissionReportDTO> generateCommissionReport(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );







}