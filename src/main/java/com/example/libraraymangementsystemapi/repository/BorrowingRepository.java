package com.example.libraraymangementsystemapi.repository;

import com.example.libraraymangementsystemapi.entity.Borrowing;
import com.example.libraraymangementsystemapi.entity.embeddedId.BorrowingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, BorrowingId> {

    @Query("SELECT b FROM Borrowing b WHERE   b.book.id = :bookId AND b.borrower.id = :borrowerId AND b.returnDate IS NULL")
    Borrowing findByBookIdAndBorrowerId(Long bookId, Long borrowerId);

    @Query("SELECT b FROM Borrowing b WHERE b.id.borrowerId = :borrowerId")
    Page<Borrowing> findAllByBorrowerId(Long borrowerId, Pageable pageable);

    @Query("SELECT b FROM Borrowing b WHERE b.id.borrowerId = :borrowerId AND b.returnDate IS NULL")
    Page<Borrowing> findActiveBorrowingsByBorrowerId(Long borrowerId, Pageable pageable);

    @Query("SELECT b FROM Borrowing b WHERE b.returnDate IS NULL")
    Page<Borrowing> findActiveBorrowings(Pageable pageable);

    @Query("SELECT b FROM Borrowing b WHERE b.dueDate < CURRENT_DATE AND b.returnDate IS NULL")
    Page<Borrowing> findOverdueBorrowings(Pageable pageable);
}