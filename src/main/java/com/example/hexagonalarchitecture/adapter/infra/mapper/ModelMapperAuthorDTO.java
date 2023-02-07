package com.example.hexagonalarchitecture.adapter.infra.mapper;

import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.dto.author.AuthorSaveDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorAddBooksDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorUpdateDTO;
import com.example.hexagonalarchitecture.application.ports.in.AuthorDTOMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class ModelMapperAuthorDTO implements AuthorDTOMapper {

    private final ModelMapper mapper;

    @Override
    public Author mapSaveDTO(AuthorSaveDTO authorSaveDTO) {
        return mapper.map(authorSaveDTO, Author.class);
    }

    @Override
    public Author mapAddBooksDTO(AuthorAddBooksDTO authorAddBooksDTO) {
        return mapper.map(authorAddBooksDTO, Author.class);
    }

    @Override
    public Author mapUpdateDTO(AuthorUpdateDTO authorUpdateDTO) {
        var dtoMapped = mapper.map(authorUpdateDTO, AuthorAddBooksDTO.class);
        var mapped = mapAddBooksDTO(dtoMapped);
        mapped.setName(authorUpdateDTO.getName());
        return mapped;
    }
}
