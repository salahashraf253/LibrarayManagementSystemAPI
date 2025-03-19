package com.example.libraraymangementsystemapi.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BorrowingFetchRequest {
    private int page = 0;
    private int size = 5;
    private String sortBy = "id.borrowerId";
    private boolean ascending = true;
}
