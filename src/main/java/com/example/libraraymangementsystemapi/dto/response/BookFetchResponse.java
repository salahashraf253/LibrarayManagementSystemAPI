package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class BookFetchResponse {
    private List<BookResponse> books;
    private PaginationData paginationData;
}