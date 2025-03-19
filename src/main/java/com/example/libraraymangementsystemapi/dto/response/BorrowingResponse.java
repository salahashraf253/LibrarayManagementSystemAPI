package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BorrowingResponse {
    private Long borrowerId;
    private Long bookId;
    private LocalDateTime checkoutDate;
    private LocalDateTime dueDate;

    private LocalDateTime returnDate;

    public BorrowingResponse(Long borrowerId, Long bookId, LocalDateTime checkoutDate, LocalDateTime dueDate) {
        this.borrowerId = borrowerId;
        this.bookId = bookId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }
}
