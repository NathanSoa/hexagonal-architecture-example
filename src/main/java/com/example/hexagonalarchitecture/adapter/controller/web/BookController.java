package com.example.hexagonalarchitecture.adapter.controller.web;

import com.example.hexagonalarchitecture.application.domain.Book;
import com.example.hexagonalarchitecture.application.dto.book.BookInputDTO;
import com.example.hexagonalarchitecture.application.ports.in.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Book> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(bookService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookInputDTO bookInputDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookInputDTO));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Book> update(@PathVariable UUID uuid, @RequestBody BookInputDTO bookInputDTO) {
        return ResponseEntity.ok(bookService.update(bookInputDTO, uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid){
        bookService.delete(uuid);
        return ResponseEntity.ok("Book deleted succesfully");
    }
}
