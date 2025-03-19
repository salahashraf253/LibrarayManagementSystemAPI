package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.dto.request.BorrowingFetchRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.BorrowingFetchResponse;
import com.example.libraraymangementsystemapi.service.BorrowingService;
import com.example.libraraymangementsystemapi.service.ReportExportService;
import com.example.libraraymangementsystemapi.util.ExtraDataUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/borrowings")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class BorrowingAdminController {
    private BorrowingService borrowingService;
    private ExtraDataUtil extraDataUtil;
    private ReportExportService reportExportService;

    @GetMapping("/overdue")
    public ResponseEntity<ApiResponse<BorrowingFetchResponse>> getBorrowedBooks(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @ModelAttribute BorrowingFetchRequest borrowingFetchRequest
    ) {
        BorrowingFetchResponse response = borrowingService.getOverdueBooks(borrowingFetchRequest);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<BorrowingFetchResponse>> getActiveBorrowingsByBorrowerId(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @ModelAttribute BorrowingFetchRequest borrowingFetchRequest
    ) {
        BorrowingFetchResponse response = borrowingService.getActiveBorrowings(borrowingFetchRequest);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

    @GetMapping("/active/{borrowerId}")
    public ResponseEntity<ApiResponse<BorrowingFetchResponse>> getActiveBorrowingsByBorrowerId(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @PathVariable Long borrowerId,
            @ModelAttribute BorrowingFetchRequest borrowingFetchRequest
    ) {
        BorrowingFetchResponse response = borrowingService.getActiveBorrowingsByBorrowerId(borrowerId, borrowingFetchRequest);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<ApiResponse<BorrowingFetchResponse>> getReport(
            @ModelAttribute BorrowingFetchRequest borrowingFetchRequest,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        BorrowingFetchResponse response = borrowingService.getReport(borrowingFetchRequest,startDate,endDate);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), response));
    }
    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam String format) throws IOException {
        return reportExportService.exportReport(startDate, endDate, format);
    }

}
