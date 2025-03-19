package com.example.libraraymangementsystemapi.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ReturnBookRequest {
    private Long borrowerId;
    private Long bookId;
}
