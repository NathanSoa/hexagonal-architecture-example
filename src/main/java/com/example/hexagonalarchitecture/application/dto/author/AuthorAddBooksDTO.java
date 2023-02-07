package com.example.hexagonalarchitecture.application.dto.author;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AuthorAddBooksDTO {

    private List<UUID> books;
}
