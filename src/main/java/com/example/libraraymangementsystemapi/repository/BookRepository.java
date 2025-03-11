package com.example.libraraymangementsystemapi.repository;

import com.example.libraraymangementsystemapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR b.title LIKE %:title%) AND " +
            "(:author IS NULL OR b.author LIKE %:author%) AND " +
            "(:isbn IS NULL OR b.isbn = :isbn)")
    Page<Book> findByTitleOrAuthorOrIsbn(
            @Param("title") String title,
            @Param("author") String author,
            @Param("isbn") String isbn,
            Pageable pageable);
}
