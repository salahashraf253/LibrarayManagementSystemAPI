package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class BookResponse {
    private String title;
    private String author;
    private String ISBN;
    private String shelfLocation;
    private int quantity;
}
