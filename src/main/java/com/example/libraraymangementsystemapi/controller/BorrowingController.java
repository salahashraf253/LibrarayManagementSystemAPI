package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.Resolvers.Token;
import com.example.libraraymangementsystemapi.dto.request.BorrowingFetchRequest;
import com.example.libraraymangementsystemapi.dto.request.CheckoutRequest;
import com.example.libraraymangementsystemapi.dto.request.ReturnBookRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.BorrowingFetchResponse;
import com.example.libraraymangementsystemapi.dto.response.BorrowingResponse;
import com.example.libraraymangementsystemapi.entity.Borrowing;
import com.example.libraraymangementsystemapi.service.BorrowingService;
import com.example.libraraymangementsystemapi.util.ExtraDataUtil;
import com.example.libraraymangementsystemapi.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowings")
@PreAuthorize("hasRole('BORROWER')")
public class BorrowingController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ExtraDataUtil extraDataUtil;
    @Autowired
    private BorrowingService borrowingService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<BorrowingFetchResponse>> getBorrowedBooksByBorrowerId(
            @Token String token, HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @ModelAttribute BorrowingFetchRequest borrowingFetchRequest
    ) {
        Long borrowerId = jwtUtil.extractUserId(token);
        BorrowingFetchResponse response = borrowingService.getBorrowedBooksByBorrowerId(borrowerId, borrowingFetchRequest);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<BorrowingResponse>> checkout(
            @Token String token,
            @RequestBody CheckoutRequest checkoutRequest
    ) {
        Long userId = jwtUtil.extractUserId(token);
        if (!userId.equals(checkoutRequest.getBorrowerId())) {
            throw new AccessDeniedException("You are not authorized to perform this action");
        }
        BorrowingResponse response = borrowingService.checkout(checkoutRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), response));
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse<?>> returnBook(
            @Token String token,
            @RequestBody ReturnBookRequest returnBookRequest
    ) {
        Long userId = jwtUtil.extractUserId(token);
        if (!userId.equals(returnBookRequest.getBorrowerId())) {
            throw new AccessDeniedException("You are not authorized to perform this action");
        }
        Borrowing borrowing = borrowingService.returnBook(returnBookRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), borrowing));
    }
}