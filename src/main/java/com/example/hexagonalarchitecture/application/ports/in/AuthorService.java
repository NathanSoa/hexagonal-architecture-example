package com.example.hexagonalarchitecture.application.ports.in;

import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.dto.author.AuthorSaveDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorAddBooksDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    Author findById(UUID uuid);
    List<Author> findAll();
    Author save(AuthorSaveDTO authorSaveDTO);
    Author update(AuthorUpdateDTO authorUpdateDTO, UUID uuid);
    Author addBooks(AuthorAddBooksDTO authorAddBooksDTO, UUID uuid);
    void delete(UUID uuid);
}
