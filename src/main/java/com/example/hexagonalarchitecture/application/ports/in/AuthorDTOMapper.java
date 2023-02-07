package com.example.hexagonalarchitecture.application.ports.in;

import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.dto.author.AuthorSaveDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorAddBooksDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorUpdateDTO;

public interface AuthorDTOMapper {

    Author mapSaveDTO(AuthorSaveDTO authorSaveDTO);
    Author mapAddBooksDTO(AuthorAddBooksDTO authorAddBooksDTO);
    Author mapUpdateDTO(AuthorUpdateDTO authorUpdateDTO);
}
