package com.example.hexagonalarchitecture.application.dto.book;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookInputDTO {

    private String title;
    private LocalDate publicationDate;
    private String isbn;
    private String publisher;
}
