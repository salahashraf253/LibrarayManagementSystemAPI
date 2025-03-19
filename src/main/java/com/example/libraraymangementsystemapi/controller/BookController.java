package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.config.ratelimit.RateLimited;
import com.example.libraraymangementsystemapi.dto.request.BookFetchRequest;
import com.example.libraraymangementsystemapi.dto.request.BookRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.BookFetchResponse;
import com.example.libraraymangementsystemapi.dto.response.BookResponse;
import com.example.libraraymangementsystemapi.dto.response.DeleteResponse;
import com.example.libraraymangementsystemapi.service.BookService;
import com.example.libraraymangementsystemapi.util.ExtraDataUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;
    private ExtraDataUtil extraDataUtil;

    @RateLimited
    @GetMapping()
    public ResponseEntity<ApiResponse<BookFetchResponse>> fetchBooks(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @ModelAttribute BookFetchRequest bookFetchRequest
    ) {
        BookFetchResponse response = bookService.findAll(bookFetchRequest);

        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBook(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @PathVariable long id
    ) {
        BookResponse response = bookService.getBook(id);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookResponse>> saveBook(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @RequestBody BookRequest bookRequest) {
        BookResponse response = bookService.saveBook(bookRequest);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeleteResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        DeleteResponse response = new DeleteResponse(HttpStatus.OK.value(), "Book deleted successfully");
        return ResponseEntity.ok(response);
    }
}
