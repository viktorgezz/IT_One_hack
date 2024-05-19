package ru.viktorgezz.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.viktorgezz.project1.util.CreatePDFReport;

import java.io.IOException;

@RestController
public class PdfController {

    private final CreatePDFReport createPDFReport;

    @Autowired
    public PdfController(CreatePDFReport createPDFReport) {
        this.createPDFReport = createPDFReport;
    }

    @GetMapping("/pdf/{idAccount}")
    public ResponseEntity<byte[]> getPdf(@PathVariable int idAccount) {
        try {
            byte[] data = createPDFReport.getPdfFiles(idAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (Exception  e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
