package com.example.libraraymangementsystemapi.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BookRequest {
    private String title;
    private String author;
    private String isbn;
    private String shelfLocation;
    private int quantity;
}
