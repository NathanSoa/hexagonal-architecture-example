package com.example.hexagonalarchitecture.application.domain;

import com.example.hexagonalarchitecture.application.domain.valueobjects.ISBN;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class Book {

    private UUID id;
    private String title;
    private LocalDate publicationDate;
    private ISBN isbn;
    private List<Author> authors;
    private String publisher;

}
