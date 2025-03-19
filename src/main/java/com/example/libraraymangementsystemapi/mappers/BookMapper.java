package com.example.libraraymangementsystemapi.mappers;

import com.example.libraraymangementsystemapi.dto.request.BookRequest;
import com.example.libraraymangementsystemapi.dto.response.BookResponse;
import com.example.libraraymangementsystemapi.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public  BookResponse sourceToDestination(Book book) {
        return new BookResponse(book.getId(),book.getTitle(), book.getAuthor(),
                book.getIsbn(), book.getShelfLocation(),
                book.getQuantity());
    }
    public  Book destinationToSource(BookRequest bookReq) {
        return new Book(bookReq.getTitle(), bookReq.getAuthor(),
                bookReq.getIsbn(), bookReq.getShelfLocation(),
                bookReq.getQuantity());
    }
}
