//package com.example.CarRental.controller;
//
//
//import com.example.CarRental.service.impl.PdfService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.ByteArrayInputStream;
//
//@RestController
//@RequestMapping("/api")
//public class PdfController {
//
//    @Autowired
//    private PdfService pdfService;
//    @GetMapping("/createpdf")
//    public ResponseEntity<InputStreamResource> createPdf(){
//
//        ByteArrayInputStream pdf = pdfService.createPdf();
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        httpHeaders.add("Content-Disposition","inline;file=lcwd.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(httpHeaders)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(pdf));
//
//    }
//}
