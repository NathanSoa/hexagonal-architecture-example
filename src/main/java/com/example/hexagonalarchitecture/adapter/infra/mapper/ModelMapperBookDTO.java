package com.example.hexagonalarchitecture.adapter.infra.mapper;

import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.dto.book.BookInputDTO;
import com.example.hexagonalarchitecture.application.ports.in.BookDTOMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class ModelMapperBookDTO implements BookDTOMapper {

    private final ModelMapper mapper;
    @Override
    public Book mapInputDTO(BookInputDTO bookInputDTO) {
        return mapper.map(bookInputDTO, Book.class);
    }
}
