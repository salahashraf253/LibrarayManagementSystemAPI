package com.example.libraraymangementsystemapi.dto.request;

import lombok.Data;
import lombok.Getter;
import java.time.LocalDateTime;
@Data
@Getter
public class CheckoutRequest {
    private Long borrowerId;
    private Long bookId;
    private LocalDateTime returnDate;
}
