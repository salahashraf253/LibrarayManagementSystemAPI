package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BorrowingFetchResponse {
    private List<BorrowingResponse> borrowings;
    private PaginationData paginationData;
}
