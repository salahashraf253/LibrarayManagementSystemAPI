package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.mappers.BookMapper;
import com.example.libraraymangementsystemapi.dto.request.BookFetchRequest;
import com.example.libraraymangementsystemapi.dto.request.BookRequest;
import com.example.libraraymangementsystemapi.dto.response.BookFetchResponse;
import com.example.libraraymangementsystemapi.dto.response.BookResponse;
import com.example.libraraymangementsystemapi.dto.response.PaginationData;
import com.example.libraraymangementsystemapi.entity.Book;
import com.example.libraraymangementsystemapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private PaginationService paginationService;
    private BookMapper bookMapper;

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO bOOK PRESENT WITH ID = " + id));
    }

    public BookResponse getBook(Long id){
        Book book= findBookById(id);
        return bookMapper.sourceToDestination(book);
    }
    public BookFetchResponse findAll(BookFetchRequest request) {
        Pageable pageable = paginationService.createPageable(request.getPage(), request.getSize(),
                request.getSortBy(), request.isAscending());
        Page<Book> bookPage = bookRepository.findByTitleOrAuthorOrIsbn(
                request.getTitle(), request.getAuthor(),
                request.getIsbn(), pageable);

        List<BookResponse> books = bookPage.getContent()
                .stream()
                .map(bookMapper::sourceToDestination)
                .toList();
        PaginationData paginationData = new PaginationData(
                bookPage.getNumber(), bookPage.getTotalPages(),
                bookPage.getTotalElements(), bookPage.getSize());

        return new BookFetchResponse(books, paginationData);
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        Book bookToSave = bookMapper.destinationToSource(bookRequest);
        Book savedBook = bookRepository.save(bookToSave);
        return bookMapper.sourceToDestination(savedBook);
    }

    public void deleteBook(Long id) {
        Book bookToDelete = findBookById(id);
        bookRepository.delete(bookToDelete);
    }

}
