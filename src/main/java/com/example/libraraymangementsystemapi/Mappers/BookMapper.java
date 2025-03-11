package com.example.libraraymangementsystemapi.Mappers;

import com.example.libraraymangementsystemapi.dto.request.BookRequest;
import com.example.libraraymangementsystemapi.dto.response.BookResponse;
import com.example.libraraymangementsystemapi.entity.Book;

public class BookMapper {
    public static BookResponse sourceToDestination(Book book) {
        return new BookResponse(book.getTitle(), book.getAuthor(),
                book.getIsbn(), book.getShelfLocation(),
                book.getQuantity());
    }
    public static Book destinationToSource(BookRequest bookReq) {
        return new Book(bookReq.getTitle(), bookReq.getAuthor(),
                bookReq.getIsbn(), bookReq.getShelfLocation(),
                bookReq.getQuantity());
    }
}
