package com.example.CarRental.controller;


import com.example.CarRental.dto.MonthlyReportDTO;
import com.example.CarRental.service.BookingService;
import com.example.CarRental.service.impl.MonthlyReportService;
import com.example.CarRental.service.impl.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MonthlyReportController {

//    private final BookingService bookingService;
//
//    @Autowired
//    public MonthlyReportController(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }
//
//    @GetMapping("/monthly-report")
//    public ResponseEntity<List<MonthlyReportDTO>> getMonthlyReport(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//
//        List<MonthlyReportDTO> report = bookingService.getMonthlyReport(startDate, endDate);
//        return ResponseEntity.ok(report);
//    }


//    private final MonthlyReportService monthlyReportService;
//
//
//
//    public MonthlyReportController(MonthlyReportService monthlyReportService) {
//        this.monthlyReportService = monthlyReportService;
//    }
//
//    @GetMapping("/monthly-report")
//    public List<MonthlyReportDTO> generateMonthlyReport(
//            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return monthlyReportService.generateMonthlyReport(startDate, endDate);
//    }



    private final MonthlyReportService monthlyReportService;
    private final PdfService pdfService;

    @Autowired
    public MonthlyReportController(MonthlyReportService monthlyReportService, PdfService pdfService) {
        this.monthlyReportService = monthlyReportService;
        this.pdfService = pdfService;
    }

    @GetMapping("/monthly-report")
    public ResponseEntity<InputStreamResource> generateMonthlyReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<MonthlyReportDTO> monthlyReports = monthlyReportService.generateMonthlyReport(startDate, endDate);
        ByteArrayInputStream pdf = pdfService.createPdf(monthlyReports,startDate,endDate);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline;file=monthly_report.pdf");

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }




}
