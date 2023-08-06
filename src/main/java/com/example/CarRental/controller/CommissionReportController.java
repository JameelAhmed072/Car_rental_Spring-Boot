package com.example.CarRental.controller;

import com.example.CarRental.dto.CommissionReportDTO;
import com.example.CarRental.service.impl.CommissionPDFService;
import com.example.CarRental.service.impl.CommissionReportService;
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
public class CommissionReportController {

    private final CommissionReportService  commissionReportService;

    private final CommissionPDFService commissionPDFService;

    public CommissionReportController(CommissionReportService commissionReportService, CommissionPDFService commissionPDFService) {
        this.commissionReportService = commissionReportService;
        this.commissionPDFService = commissionPDFService;
    }

    @GetMapping("/commission-report")
    public ResponseEntity<InputStreamResource> generateMonthlyReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<CommissionReportDTO> commissionReportReports = commissionReportService.generateCommissionReport(startDate, endDate);
        ByteArrayInputStream pdf = commissionPDFService.createPdf(commissionReportReports,startDate,endDate);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline;file=Commission_report.pdf");

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}
