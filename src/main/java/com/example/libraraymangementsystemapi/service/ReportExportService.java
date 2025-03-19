package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.dto.response.BorrowingResponse;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportExportService {
    private final BorrowingService borrowingService;

    public ResponseEntity<ByteArrayResource> exportReport(LocalDateTime startDate, LocalDateTime endDate, String format) throws IOException {
        List<BorrowingResponse> records = borrowingService.getBorrowingData(startDate, endDate);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String filename;
        if ("csv".equalsIgnoreCase(format)) {
            filename = "borrowing_report.csv";
            writeCSV(records, outputStream);
        } else {
            filename = "borrowing_report.xlsx";
            writeExcel(records, outputStream);
        }

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    private void writeCSV(List<BorrowingResponse> records, OutputStream outputStream) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(outputStream), CSVFormat.DEFAULT.withHeader("Borrower ID", "Book ID", "Checkout Date", "Due Date", "Return Date"))) {
            for (BorrowingResponse borrowingResponse : records) {
                printer.printRecord(
                        borrowingResponse.getBorrowerId(),
                        borrowingResponse.getBookId(),
                        borrowingResponse.getCheckoutDate(),
                        borrowingResponse.getDueDate(),
                        borrowingResponse.getReturnDate() != null ? borrowingResponse.getReturnDate() : "Not Returned"
                );
            }
        }
    }

    private void writeExcel(List<BorrowingResponse> records, OutputStream outputStream) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Borrowing Report");

            Row header = sheet.createRow(0);
            String[] columns = {"Borrower ID", "Book ID", "Checkout Date", "Due Date", "Return Date"};
            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (BorrowingResponse borrowingResponse : records) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(borrowingResponse.getBorrowerId());
                row.createCell(1).setCellValue(borrowingResponse.getBookId());
                row.createCell(2).setCellValue(borrowingResponse.getCheckoutDate().toString());
                row.createCell(3).setCellValue(borrowingResponse.getDueDate().toString());
                row.createCell(4).setCellValue(borrowingResponse.getReturnDate() != null ? borrowingResponse.getReturnDate().toString() : "Not Returned");
            }

            workbook.write(outputStream);
        }
    }
}
