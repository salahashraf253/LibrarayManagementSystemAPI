package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.dto.request.BorrowingFetchRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.BorrowingFetchResponse;
import com.example.libraraymangementsystemapi.service.BorrowingService;
import com.example.libraraymangementsystemapi.util.ExtraDataUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowings")
@PreAuthorize("hasRole('ADMIN')")
public class BorrowingAdminController {
    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private ExtraDataUtil extraDataUtil;

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
}
