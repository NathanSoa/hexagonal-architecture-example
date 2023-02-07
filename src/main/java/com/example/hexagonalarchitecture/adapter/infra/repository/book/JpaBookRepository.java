package com.example.hexagonalarchitecture.adapter.infra.repository.book;

import com.example.hexagonalarchitecture.adapter.infra.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaBookRepository extends JpaRepository<BookEntity, UUID> {
}
