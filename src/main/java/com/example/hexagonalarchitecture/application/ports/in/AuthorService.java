package com.example.hexagonalarchitecture.application.ports.in;

import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.dto.AuthorCreateDTO;
import com.example.hexagonalarchitecture.application.dto.AuthorUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    Author findById(UUID uuid);
    List<Author> findAll();
    Author create(AuthorCreateDTO authorCreateDTO);
    Author update(AuthorUpdateDTO authorUpdateDTO, UUID uuid);
    void delete(UUID uuid);
}
