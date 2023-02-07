package com.example.hexagonalarchitecture.application.ports.out;

import com.example.hexagonalarchitecture.application.domain.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository {

    Author findById(UUID uuid);
    Boolean existsById(UUID uuid);
    List<Author> findAll();
    Author create(Author author);
    Author update(Author author);
    Author addBooks(Author author);
    void delete(UUID uuid);
}
