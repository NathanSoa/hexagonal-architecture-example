package com.example.hexagonalarchitecture.application.service;

import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.dto.book.BookInputDTO;
import com.example.hexagonalarchitecture.application.ports.in.BookDTOMapper;
import com.example.hexagonalarchitecture.application.ports.in.BookService;
import com.example.hexagonalarchitecture.application.ports.out.BookRepository;
import com.example.hexagonalarchitecture.config.exception.custom.EntityDoesNotExistException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDTOMapper mapper;
    private final BookRepository bookRepository;

    @Override
    public Book findById(UUID uuid) {
        throwExceptionIfBookDoesNotExist(uuid);
        return bookRepository.findById(uuid);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(BookInputDTO bookInputDTO) {
        Book book = mapper.mapInputDTO(bookInputDTO);
        return bookRepository.create(book);
    }

    @Override
    public Book update(BookInputDTO bookInputDTO, UUID uuid) {
        throwExceptionIfBookDoesNotExist(uuid);
        Book book = mapper.mapInputDTO(bookInputDTO);
        book.setId(uuid);
        return bookRepository.update(book);
    }

    @Override
    public void delete(UUID uuid) {
        throwExceptionIfBookDoesNotExist(uuid);
        bookRepository.delete(uuid);
    }

    private void throwExceptionIfBookDoesNotExist(UUID uuid) {
        if(!bookRepository.existsById(uuid))
            throw new EntityDoesNotExistException("Cannot find any book with id: " + uuid.toString());
    }
}
