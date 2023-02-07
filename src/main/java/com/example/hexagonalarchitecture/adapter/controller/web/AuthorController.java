package com.example.hexagonalarchitecture.adapter.controller.web;

import com.example.hexagonalarchitecture.application.domain.Author;
import com.example.hexagonalarchitecture.application.dto.author.AuthorAddBooksDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorSaveDTO;
import com.example.hexagonalarchitecture.application.dto.author.AuthorUpdateDTO;
import com.example.hexagonalarchitecture.application.ports.in.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Author> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(authorService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody AuthorSaveDTO authorSaveDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(authorSaveDTO));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Author> update(@PathVariable UUID uuid, @RequestBody AuthorUpdateDTO authorUpdateDTO) {
        return ResponseEntity.ok(authorService.update(authorUpdateDTO, uuid));
    }

    @PatchMapping("/{uuid}/books")
    public ResponseEntity<Author> addBooks(@PathVariable UUID uuid, @RequestBody AuthorAddBooksDTO authorAddBooksDTO){
        return ResponseEntity.ok(authorService.addBooks(authorAddBooksDTO, uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid){
        authorService.delete(uuid);
        return ResponseEntity.ok("Author deleted succesfully");
    }
}
