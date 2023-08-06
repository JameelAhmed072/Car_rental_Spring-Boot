////package com.example.CarRental.service.impl;
////
////import com.lowagie.text.*;
////import com.lowagie.text.pdf.PdfWriter;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.stereotype.Service;
////
////import java.io.ByteArrayInputStream;
////import java.io.ByteArrayOutputStream;
////
////@Service
////public class PdfService {
////
////    private Logger logger = LoggerFactory.getLogger(PdfService.class);
////    public ByteArrayInputStream createPdf(){
////
////        logger.info("Create PDF Started : ");
////
////        String title = "Welcome I am Jameel Ahmed";
////        String content = " I am Student of Muhammad Ali jinnah university";
////
////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////
////        Document document = new Document();
////
////        PdfWriter.getInstance(document,out);
////
////        document.open();
////
////        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
////
////        Paragraph titlePara = new Paragraph(title,titleFont);
////        titlePara.setAlignment(Element.ALIGN_CENTER);
////
////
////        document.add(titlePara);
////
////        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA,18);
////
////        Paragraph paragraph = new Paragraph(content);
////        paragraph.add(new Chunk("Wow this text is added after creating paragraph"));
////        document.add(paragraph);
////
////
////
////        document.close();
////
////
////        return new ByteArrayInputStream(out.toByteArray());
////    }
////}
//
//
//
//
//
//
//package com.example.CarRental.service.impl;
//
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//
//@Service
//public class PdfService {
//
//    private Logger logger = LoggerFactory.getLogger(PdfService.class);
//
//    public ByteArrayInputStream createPdf(){
//
//        logger.info("Create PDF Started : ");
//
//        String title = "Welcome I am Jameel Ahmed";
//        String content = "I am Student of Muhammad Ali Jinnah University";
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        Document document = new Document();
//
//        try {
//            PdfWriter.getInstance(document, out);
//            document.open();
//
//            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
//
//            Paragraph titlePara = new Paragraph(title, titleFont);
//            titlePara.setAlignment(Element.ALIGN_CENTER);
//
//            document.add(titlePara);
//
//            Font paraFont = FontFactory.getFont(FontFactory.HELVETICA, 18);
//
//            Paragraph paragraph = new Paragraph(content);
//            paragraph.add(new Chunk(" and it is suitated in Karachi"));
//            document.add(paragraph);
//
//            // Create a table with 6 columns and 20 rows
//            PdfPTable table = new PdfPTable(6);
//            table.setWidthPercentage(80); // Adjust the table width (percentage of page width)
//            table.setHorizontalAlignment(Element.ALIGN_CENTER); // Align the table to the center
//
//            // Add table headers
//            for (int i = 1; i <= 6; i++) {
//                PdfPCell cell = new PdfPCell(new Phrase("Header " + i));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//            }
//
//            // Add table rows
//            for (int row = 1; row <= 20; row++) {
//                for (int col = 1; col <= 6; col++) {
//                    PdfPCell cell = new PdfPCell(new Phrase("Row " + row + ", Col " + col));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    table.addCell(cell);
//                }
//            }
//
//            document.add(table);
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } finally {
//            document.close();
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//}


package com.example.CarRental.service.impl;

import com.example.CarRental.dto.MonthlyReportDTO;
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
public class PdfService {

    private Logger logger = LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdf(List<MonthlyReportDTO> monthlyReports, LocalDate startDate, LocalDate endDate) {

        logger.info("Create PDF Started : ");

        String title = "Welcome to Monthly Report";



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
            String[] headers = {"ID", "Customer Name", "Vehicle Name", "Booking Date",
                    "Completed Date", "Price", "Commission", "Booking Status"};

            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                reportTable.addCell(cell);
            }

            // Add report rows
            for (MonthlyReportDTO report : monthlyReports) {
                reportTable.addCell(report.getId().toString());
                reportTable.addCell(report.getCustomerName());
                reportTable.addCell(report.getVehicleName());
                reportTable.addCell(report.getBookingDate().toString());
                reportTable.addCell(report.getCompletedDate().toString());
                reportTable.addCell(report.getPrice().toString());
                reportTable.addCell(report.getCommission().toString());
                reportTable.addCell(report.getBookingStatus());
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
