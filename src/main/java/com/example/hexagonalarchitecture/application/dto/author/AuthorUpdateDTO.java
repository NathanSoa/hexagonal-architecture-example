package com.example.hexagonalarchitecture.application.dto.author;

import com.example.hexagonalarchitecture.application.domain.valueobjects.Name;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AuthorUpdateDTO {

    private Name name;
    private List<UUID> books;
}
