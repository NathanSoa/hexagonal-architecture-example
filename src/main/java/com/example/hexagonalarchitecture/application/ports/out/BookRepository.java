package com.example.hexagonalarchitecture.application.ports.out;

import com.example.hexagonalarchitecture.application.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository {

    Book findById(UUID uuid);
    Boolean existsById(UUID uuid);
    List<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    void delete(UUID uuid);
}
