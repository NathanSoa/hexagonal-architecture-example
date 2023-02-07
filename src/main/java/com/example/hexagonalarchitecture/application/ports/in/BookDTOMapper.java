package com.example.hexagonalarchitecture.application.ports.in;

import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.dto.book.BookInputDTO;

public interface BookDTOMapper {

    Book mapInputDTO(BookInputDTO bookInputDTO);
}
