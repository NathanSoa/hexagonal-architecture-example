package com.example.hexagonalarchitecture.config.bean;

import com.example.hexagonalarchitecture.adapter.infra.mapper.ModelMapperAuthorDTO;
import com.example.hexagonalarchitecture.adapter.infra.mapper.ModelMapperBookDTO;
import com.example.hexagonalarchitecture.application.ports.in.AuthorDTOMapper;
import com.example.hexagonalarchitecture.application.ports.in.AuthorService;
import com.example.hexagonalarchitecture.application.ports.in.BookDTOMapper;
import com.example.hexagonalarchitecture.application.ports.in.BookService;
import com.example.hexagonalarchitecture.application.ports.out.AuthorRepository;
import com.example.hexagonalarchitecture.application.ports.out.BookRepository;
import com.example.hexagonalarchitecture.application.service.AuthorServiceImpl;
import com.example.hexagonalarchitecture.application.service.BookServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AuthorDTOMapper authorDTOMapper(ModelMapper mapper){
        return new ModelMapperAuthorDTO(mapper);
    }

    @Bean
    public AuthorService authorService(AuthorDTOMapper authorDTOMapper, AuthorRepository authorRepository, BookRepository bookRepository) {
        return new AuthorServiceImpl(authorDTOMapper, authorRepository, bookRepository);
    }

    @Bean
    public BookDTOMapper bookDTOMapper(ModelMapper mapper) {
        return new ModelMapperBookDTO(mapper);
    }

    @Bean
    public BookService bookService(BookDTOMapper bookDTOMapper, BookRepository bookRepository) {
        return new BookServiceImpl(bookDTOMapper, bookRepository);
    }
}
