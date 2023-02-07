package com.example.hexagonalarchitecture.application.service;

import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.dto.author.AuthorSaveDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorAddBooksDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorUpdateDTO;
import com.example.hexagonalarchitecture.application.ports.in.AuthorDTOMapper;
import com.example.hexagonalarchitecture.application.ports.in.AuthorService;
import com.example.hexagonalarchitecture.application.ports.out.AuthorRepository;
import com.example.hexagonalarchitecture.application.ports.out.BookRepository;
import com.example.hexagonalarchitecture.config.exception.custom.EntityDoesNotExistException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDTOMapper dtoMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public Author findById(UUID uuid) {
        throwExceptionIfAuthorDoesNotExist(uuid);

        return authorRepository.findById(uuid);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(AuthorSaveDTO authorSaveDTO) {
        Author author = dtoMapper.mapSaveDTO(authorSaveDTO);
        return authorRepository.create(author);
    }

    @Override
    public Author update(AuthorUpdateDTO authorUpdateDTO, UUID uuid) {
        throwExceptionIfAuthorDoesNotExist(uuid);
        throwExceptionIfAnyBookDoesNotExist(authorUpdateDTO.getBooks());

        Author author = dtoMapper.mapUpdateDTO(authorUpdateDTO);
        author.setId(uuid);
        return authorRepository.update(author);
    }

    @Override
    public Author addBooks(AuthorAddBooksDTO authorAddBooksDTO, UUID uuid) {
        throwExceptionIfAuthorDoesNotExist(uuid);
        throwExceptionIfAnyBookDoesNotExist(authorAddBooksDTO.getBooks());

        Author author = authorRepository.findById(uuid);
        Author authorWithBooks = dtoMapper.mapAddBooksDTO(authorAddBooksDTO);
        author.setBooks(authorWithBooks.getBooks());
        return authorRepository.addBooks(author);
    }

    @Override
    public void delete(UUID uuid) {
        throwExceptionIfAuthorDoesNotExist(uuid);
        authorRepository.delete(uuid);
    }

    private void throwExceptionIfAuthorDoesNotExist(UUID uuid) {
        if(!authorRepository.existsById(uuid))
            throw new EntityDoesNotExistException("Cannot find any author with id: " + uuid.toString());
    }

    private void throwExceptionIfAnyBookDoesNotExist(List<UUID> booksId) {
        boolean allBookExists = booksId.parallelStream().allMatch(bookRepository::existsById);

        if(!allBookExists)
            throw new EntityDoesNotExistException("Invalid book Id was sent!");
    }
}
