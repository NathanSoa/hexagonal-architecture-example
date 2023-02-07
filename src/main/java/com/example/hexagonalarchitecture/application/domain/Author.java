package com.example.hexagonalarchitecture.application.domain;

import com.example.hexagonalarchitecture.application.domain.valueobjects.Name;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private UUID id;
    private Name name;
    private List<Book> books;
}
