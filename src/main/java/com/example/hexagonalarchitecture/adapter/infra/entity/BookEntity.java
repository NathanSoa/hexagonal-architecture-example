package com.example.hexagonalarchitecture.adapter.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    private String title;
    private LocalDate publicationDate;
    private String isbn;
    @ManyToMany(mappedBy = "books")
    private List<AuthorEntity> authors;
    private String publisher;
}
