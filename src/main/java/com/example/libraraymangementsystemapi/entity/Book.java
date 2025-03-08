package com.example.libraraymangementsystemapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books", indexes = {@Index(columnList = "ISBN"), @Index(name = "title_author_idx", columnList = "title, author")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true, nullable = false)
    private String ISBN;

    @Column(nullable = false)
    private String shelfLocation;

    @Column(nullable = false)
    private int quantity;

}
