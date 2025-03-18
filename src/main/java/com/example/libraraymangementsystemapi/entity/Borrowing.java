package com.example.libraraymangementsystemapi.entity;

import com.example.libraraymangementsystemapi.entity.EmbeddedIds.BorrowingId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "borrowings")
public class Borrowing {
    @EmbeddedId
    private BorrowingId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @MapsId("borrowerId")
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    private LocalDateTime returnDate;
}
