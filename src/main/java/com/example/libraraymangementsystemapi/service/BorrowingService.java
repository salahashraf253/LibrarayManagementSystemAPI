package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.mappers.BorrowingMapper;
import com.example.libraraymangementsystemapi.dto.request.BorrowingFetchRequest;
import com.example.libraraymangementsystemapi.dto.request.CheckoutRequest;
import com.example.libraraymangementsystemapi.dto.request.ReturnBookRequest;
import com.example.libraraymangementsystemapi.dto.response.BorrowingFetchResponse;
import com.example.libraraymangementsystemapi.dto.response.BorrowingResponse;
import com.example.libraraymangementsystemapi.dto.response.PaginationData;
import com.example.libraraymangementsystemapi.dto.response.ReturnBookResponse;
import com.example.libraraymangementsystemapi.entity.Book;
import com.example.libraraymangementsystemapi.entity.Borrower;
import com.example.libraraymangementsystemapi.entity.Borrowing;
import com.example.libraraymangementsystemapi.entity.embedded.BorrowingId;
import com.example.libraraymangementsystemapi.repository.BookRepository;
import com.example.libraraymangementsystemapi.repository.BorrowerRepository;
import com.example.libraraymangementsystemapi.repository.BorrowingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowingService {
    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private BorrowerRepository borrowerRepository;
    private BorrowingMapper borrowingMapper;
    private PaginationService paginationService;

    public BorrowingFetchResponse getBorrowedBooksByBorrowerId(Long userId, BorrowingFetchRequest request) {
        return fetchBorrowings(borrowingRepository.findAllByBorrowerId(userId, buildPageable(request)));
    }

    @Transactional
    public BorrowingResponse checkout(CheckoutRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Borrower borrower = borrowerRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new IllegalArgumentException("Borrower not found"));
        Borrowing borrowing = borrowingRepository.findByBookIdAndBorrowerId(book.getId(), borrower.getId());
        if(borrowing != null){
            throw  new IllegalStateException("You already have borrowed this book and didn't return it");
        }
        if (book.getQuantity() <= 0) {
            throw new IllegalStateException("Book is out of stock");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        borrowing = new Borrowing(new BorrowingId(request.getBookId(), request.getBorrowerId()), book, borrower, request.getDueDate());
        borrowingRepository.save(borrowing);

        return borrowingMapper.sourceToDestination(borrowing);
    }

    @Transactional
    public ReturnBookResponse returnBook(ReturnBookRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setQuantity(book.getQuantity()+1);
        book=bookRepository.save(book);

        Borrower borrower = borrowerRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new IllegalArgumentException("Borrower not found"));

        Borrowing borrowing = borrowingRepository.findByBookIdAndBorrowerId(book.getId(), borrower.getId());
        borrowing.setReturnDate(LocalDateTime.now());
        borrowing=borrowingRepository.save(borrowing);
        return new ReturnBookResponse(
                borrowing.getId().getBorrowerId(),
                borrowing.getId().getBookId(),
                borrowing.getId().getCheckoutDate(),
                borrowing.getDueDate(),
                borrowing.getReturnDate()
        );
    }

    public BorrowingFetchResponse getOverdueBooks(BorrowingFetchRequest request) {
        return fetchBorrowings(borrowingRepository.findOverdueBorrowings(buildPageable(request)));
    }

    public BorrowingFetchResponse getActiveBorrowingsByBorrowerId(Long borrowerId, BorrowingFetchRequest request) {
        return fetchBorrowings(borrowingRepository.findActiveBorrowingsByBorrowerId(borrowerId, buildPageable(request)));
    }

    public BorrowingFetchResponse getActiveBorrowings(BorrowingFetchRequest request) {
        return fetchBorrowings(borrowingRepository.findActiveBorrowings(buildPageable(request)));
    }

    private Pageable buildPageable(BorrowingFetchRequest request) {
        return paginationService.createPageable(request.getPage(), request.getSize(), request.getSortBy(), request.isAscending());
    }

    private BorrowingFetchResponse fetchBorrowings(Page<Borrowing> borrowingPage) {
        List<BorrowingResponse> borrowings = borrowingPage.getContent().stream()
                .map(borrowingMapper::sourceToDestination)
                .toList();

        PaginationData paginationData = new PaginationData(
                borrowingPage.getNumber(), borrowingPage.getTotalPages(),
                borrowingPage.getTotalElements(), borrowingPage.getSize()
        );
        return new BorrowingFetchResponse(borrowings, paginationData);
    }
}
