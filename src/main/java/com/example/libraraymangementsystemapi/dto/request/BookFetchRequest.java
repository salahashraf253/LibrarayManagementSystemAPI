package com.example.libraraymangementsystemapi.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BookFetchRequest {
    private int page = 0;
    private int size = 5;
    private String sortBy = "id";
    private boolean ascending = true;
    private String title;
    private String author;
    private String isbn;
}