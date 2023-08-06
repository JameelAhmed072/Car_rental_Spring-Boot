package com.example.CarRental.service.impl;

import com.example.CarRental.dto.CommissionReportDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class CommissionPDFService {
    private Logger logger = LoggerFactory.getLogger(CommissionPDFService.class);
    public ByteArrayInputStream createPdf(List<CommissionReportDTO> commissionReports, LocalDate startDate, LocalDate endDate) {

        logger.info("Create PDF Started : ");

        String title = "Welcome to Commission Report";



        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph datePara = new Paragraph("Report between these Dates: " + startDate.toString() + " - " + endDate.toString(), dateFont);
            datePara.setAlignment(Element.ALIGN_LEFT);
            document.add(datePara);


            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
//
            Paragraph titlePara = new Paragraph(title, titleFont);
            titlePara.setAlignment(Element.ALIGN_CENTER);


            document.add(titlePara);
            // Add some space after the title
            document.add(new Paragraph(" ")); // Empty paragraph with default spacing
            document.add(new Paragraph(" ")); // Empty paragraph with default spacing

            // Add the monthly report data to the PDF
            PdfPTable reportTable = new PdfPTable(8); // Number of columns in the report
            reportTable.setWidthPercentage(100);

            // Add table headers
            String[] headers = {"Owner Name","Vehicle Name","Price","Booking-date","Completed-date","Total Price","Commission","Total-Commission"};

            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                reportTable.addCell(cell);
            }

            // Add report rows
            for (CommissionReportDTO report : commissionReports) {
                reportTable.addCell(report.getOwnerName());
                reportTable.addCell(report.getVehicleName());
                reportTable.addCell(String.valueOf(report.getPrice()));
                reportTable.addCell(String.valueOf(report.getBookingDate()));
                reportTable.addCell(String.valueOf(report.getCompletedDate()));
                reportTable.addCell(String.valueOf(report.getTotalPrice()));
                reportTable.addCell(String.valueOf(report.getCommission()));
                reportTable.addCell(String.valueOf(report.getTotalCommission()));
            }
            document.add(reportTable);

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
