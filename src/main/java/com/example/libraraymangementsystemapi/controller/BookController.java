package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.dto.request.BookFetchRequest;
import com.example.libraraymangementsystemapi.dto.request.BookRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.BookFetchResponse;
import com.example.libraraymangementsystemapi.dto.response.BookResponse;
import com.example.libraraymangementsystemapi.service.BookService;
import com.example.libraraymangementsystemapi.util.ExtraDataUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ExtraDataUtil extraDataUtil;


    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookResponse>> saveBook(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @RequestBody BookRequest bookRequest) {
        BookResponse response = bookService.saveBook(bookRequest);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<ApiResponse<BookFetchResponse>> fetchBooks(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @ModelAttribute BookFetchRequest bookFetchRequest
    ) {
        BookFetchResponse response = bookService.findAll(bookFetchRequest);

        return extraDataUtil.buildResponse(httpRequest, includeExtraData, response, HttpStatus.OK);
    }

}
