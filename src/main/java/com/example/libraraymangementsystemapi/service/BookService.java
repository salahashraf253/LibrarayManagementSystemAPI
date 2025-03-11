package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.Mappers.BookMapper;
import com.example.libraraymangementsystemapi.dto.request.BookFetchRequest;
import com.example.libraraymangementsystemapi.dto.request.BookRequest;
import com.example.libraraymangementsystemapi.dto.response.BookFetchResponse;
import com.example.libraraymangementsystemapi.dto.response.BookResponse;
import com.example.libraraymangementsystemapi.dto.response.PaginationData;
import com.example.libraraymangementsystemapi.entity.Book;
import com.example.libraraymangementsystemapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PaginationService paginationService;

    public BookFetchResponse findAll(BookFetchRequest request) {
        Pageable pageable = paginationService.createPageable(request.getPage(), request.getSize(),
                request.getSortBy(), request.isAscending());
        Page<Book> bookPage = bookRepository.findByTitleOrAuthorOrIsbn(
                request.getTitle(), request.getAuthor(),
                request.getIsbn(), pageable);

        List<BookResponse> books = bookPage.getContent()
                .stream()
                .map(BookMapper::sourceToDestination)
                .collect(Collectors.toList());
        PaginationData paginationData = new PaginationData(
                bookPage.getNumber(), bookPage.getTotalPages(),
                bookPage.getTotalElements(), bookPage.getSize());

        return new BookFetchResponse(books, paginationData);
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        Book bookToSave = BookMapper.destinationToSource(bookRequest);
        Book savedBook = bookRepository.save(bookToSave);
        return BookMapper.sourceToDestination(savedBook);
    }
}
