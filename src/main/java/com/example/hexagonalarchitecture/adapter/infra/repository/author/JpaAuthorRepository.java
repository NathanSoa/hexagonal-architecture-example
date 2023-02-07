package com.example.hexagonalarchitecture.adapter.infra.repository.author;

import com.example.hexagonalarchitecture.adapter.infra.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, UUID> {
}
