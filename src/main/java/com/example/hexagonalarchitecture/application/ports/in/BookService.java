package com.example.hexagonalarchitecture.application.ports.in;


import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.dto.book.BookInputDTO;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book findById(UUID uuid);
    List<Book> findAll();
    Book save(BookInputDTO bookSaveDTO);
    Book update(BookInputDTO bookInputDTO, UUID uuid);
    void delete(UUID uuid);
}
