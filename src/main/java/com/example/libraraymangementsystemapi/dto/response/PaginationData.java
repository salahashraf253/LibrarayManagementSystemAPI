package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationData {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
}
