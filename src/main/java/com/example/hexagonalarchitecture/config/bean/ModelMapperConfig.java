package com.example.hexagonalarchitecture.config.bean;


import com.example.hexagonalarchitecture.adapter.infra.entity.AuthorEntity;
import com.example.hexagonalarchitecture.adapter.infra.entity.BookEntity;
import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.domain.valueobjects.ISBN;
import com.example.hexagonalarchitecture.application.domain.valueobjects.Name;
import com.example.hexagonalarchitecture.application.dto.author.AuthorAddBooksDTO;
import com.example.hexagonalarchitecture.application.dto.book.BookInputDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        createAuthorMappings(mapper);
        createBookMappings(mapper);
        return mapper;
    }

    private void createAuthorMappings(ModelMapper mapper) {
        createAuthorEntityMappingToAuthorDomain(mapper);
        createAddBooksDTOMappingToAuthorDomain(mapper);
    }
    private void createBookMappings(ModelMapper mapper) {
        createBookEntityMappingToDomain(mapper);
        createBookInputDTOMappingToDomain(mapper);
    }

    /*
        This method create the mapping between author entity and author domain,
        it converts a list of book entities to book domain list
        and teach how ModelMapper should handle with Name value object.
   */
    private void createAuthorEntityMappingToAuthorDomain(ModelMapper mapper) {
        Converter<List<BookEntity>, List<Book>> bookEntityListToBookDomainList
                = context -> context.getSource().stream().map(bookEntity ->
                    Book.builder()
                          .id(bookEntity.getUuid())
                          .title(bookEntity.getTitle())
                          .isbn(new ISBN(bookEntity.getIsbn()))
                          .publicationDate(bookEntity.getPublicationDate())
                          .publisher(bookEntity.getPublisher())
                          .authors(new ArrayList<>())
                          .build()).toList();

        mapper.typeMap(AuthorEntity.class, Author.class).addMappings(src -> {
            src.using(bookEntityListToBookDomainList);
            src.map(AuthorEntity::getBooks, Author::setBooks);
            src.<String>map(AuthorEntity::getFirstName, (author, value) -> author.getName().setFirstName(value));
            src.<String>map(AuthorEntity::getLastName, (author, value) -> author.getName().setLastName(value));
            src.map(AuthorEntity::getUuid, Author::setId);
        });
    }

    /*
        This method create the mapping beetween add book author DTO and author domain,
        it converts the uuid list into a book list that each book object only has uuid.
    */
    private void createAddBooksDTOMappingToAuthorDomain(ModelMapper mapper) {
        Converter<List<UUID>, List<Book>> uuidListToBookDomainList = context -> context.getSource().stream().map(uuid ->
                Book.builder().id(uuid).build()).toList();

        mapper.typeMap(AuthorAddBooksDTO.class, Author.class).addMappings(src -> {
            src.using(uuidListToBookDomainList);
            src.map(AuthorAddBooksDTO::getBooks, Author::setBooks);
        });
    }

    /*
        This method create the mapping between book entity and book domain,
        it converts a list of author entities to author domain list
        and teach how ModelMapper should handle with ISBN value object.
   */
    private void createBookEntityMappingToDomain(ModelMapper mapper) {
        Converter<List<AuthorEntity>, List<Author>> authorEntityListToAuthorDomainList
                = context -> context.getSource().stream().map(authorEntity ->
                Author.builder()
                        .id(authorEntity.getUuid())
                        .name(new Name(authorEntity.getFirstName(), authorEntity.getLastName()))
                        .books(new ArrayList<>())
                        .build()).toList();

        mapper.typeMap(BookEntity.class, Book.class).addMappings(src -> {
            src.using(authorEntityListToAuthorDomainList);
            src.map(BookEntity::getAuthors, Book::setAuthors);
            src.<String>map(BookEntity::getIsbn, (book,value) -> book.setIsbn(new ISBN(value)));
            src.map(BookEntity::getUuid, Book::setId);
        });
    }

    private void createBookInputDTOMappingToDomain(ModelMapper mapper) {
        mapper.typeMap(BookInputDTO.class, Book.class).addMappings(src -> {
            src.<String>map(BookInputDTO::getIsbn, (book, value) -> book.setIsbn(new ISBN(value)));
        });
    }
}
