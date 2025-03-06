package com.example.libraraymangementsystemapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "borrowings",indexes = @Index(name="unique_borrow",columnList = "book_id, borrower_id"))
public class Borrowing {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @Column(nullable = false, updatable = false)
    private LocalDateTime checkoutDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    private LocalDateTime returnDate;

    @PrePersist
    protected void onCreate() {
        this.checkoutDate = LocalDateTime.now();
    }

}
