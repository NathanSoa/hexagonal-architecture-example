package com.example.hexagonalarchitecture.adapter.infra.repository.author;

import com.example.hexagonalarchitecture.adapter.infra.entity.AuthorEntity;
import com.example.hexagonalarchitecture.adapter.infra.repository.book.JpaBookRepository;
import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.ports.out.AuthorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PersistenceAuthorRepository implements AuthorRepository {

    private final JpaAuthorRepository jpaAuthorRepository;
    private final JpaBookRepository jpaBookRepository;
    private final ModelMapper mapper;

    @Override
    public Author findById(UUID uuid) {
        var authorEntity = jpaAuthorRepository.findById(uuid).orElseThrow(RuntimeException::new);
        return mapper.map(authorEntity, Author.class);
    }

    @Override
    public Boolean existsById(UUID uuid) {
        return jpaAuthorRepository.existsById(uuid);
    }

    @Override
    public List<Author> findAll() {
        return jpaAuthorRepository.findAll().stream().map(each -> mapper.map(each, Author.class)).toList();
    }

    @Override
    public Author create(Author author) {
        var authorEntity = jpaAuthorRepository.save(mapper.map(author, AuthorEntity.class));
        author.setId(authorEntity.getUuid());
        return author;
    }

    @Override
    public Author update(Author author) {
        var authorEntity = mapper.map(author, AuthorEntity.class);
        authorEntity.setUuid(author.getId());
        jpaAuthorRepository.save(authorEntity);
        return author;
    }

    @Override
    public Author addBooks(Author author) {
        var authorEntity = mapper.map(author, AuthorEntity.class);
        authorEntity.setUuid(author.getId());
        authorEntity.setBooks(new ArrayList<>());

        author.getBooks().forEach(eachBook -> {
            authorEntity.getBooks().add(jpaBookRepository.findById(eachBook.getId()).get());
        });

        var updatedAuthorEntity = jpaAuthorRepository.save(authorEntity);
        return mapper.map(updatedAuthorEntity, Author.class);
    }

    @Override
    public void delete(UUID uuid) {
        jpaAuthorRepository.deleteById(uuid);
    }
}
