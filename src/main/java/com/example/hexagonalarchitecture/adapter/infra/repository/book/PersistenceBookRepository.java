package com.example.hexagonalarchitecture.adapter.infra.repository.book;

import com.example.hexagonalarchitecture.adapter.infra.entity.AuthorEntity;
import com.example.hexagonalarchitecture.adapter.infra.entity.BookEntity;
import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.ports.out.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PersistenceBookRepository implements BookRepository {

    private final JpaBookRepository jpaBookRepository;
    private final ModelMapper mapper;

    @Override
    public Book findById(UUID uuid) {
        return mapper.map(jpaBookRepository.findById(uuid).orElseThrow(RuntimeException::new), Book.class);
    }

    @Override
    public Boolean existsById(UUID uuid) {
        return jpaBookRepository.existsById(uuid);
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll().stream().map(each -> mapper.map(each, Book.class)).toList();
    }

    @Override
    public Book create(Book book) {
        var bookEntity = jpaBookRepository.save(mapper.map(book, BookEntity.class));
        book.setId(bookEntity.getUuid());
        return book;
    }

    @Override
    public Book update(Book book) {
        var bookEntity = mapper.map(book, BookEntity.class);
        bookEntity.setUuid(book.getId());
        jpaBookRepository.save(bookEntity);
        return book;
    }

    @Override
    public void delete(UUID uuid) {
        jpaBookRepository.deleteById(uuid);
    }
}
