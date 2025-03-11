package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class BookResponse {
    private String title;
    private String author;
    private String isbn;
    private String shelfLocation;
    private int quantity;

}
