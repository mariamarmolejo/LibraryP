package com.example.libraryp.controllers;
import com.example.libraryp.model.Book;
import com.example.libraryp.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * hace uso de una clase JpaRepository que realiza metodos CRUD de forma automatica
 */
@RestController
@RequestMapping("apiBooks")
public class Api {

    @Autowired
    private IBookRepository repositoryBook;

    // Endpoint para obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = repositoryBook.findAll();
        return ResponseEntity.ok().body(books);
    }

    // Endpoint para obtener un libro por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return repositoryBook.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para agregar un nuevo libro
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = repositoryBook.save(book);
        return ResponseEntity.ok().body(newBook);
    }

    // Endpoint para actualizar un libro existente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updateBook) {
        return repositoryBook.findById(id)
                .map(oldBook -> {
                    oldBook.setTitle(updateBook.getTitle());
                    oldBook.setAuthor(updateBook.getAuthor());
                    oldBook.setPublicationYear(updateBook.getPublicationYear());
                    oldBook.setGenre(updateBook.getGenre());
                    Book updateBookEntity = repositoryBook.save(oldBook);
                    return ResponseEntity.ok().body(updateBookEntity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para eliminar un libro por su ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repositoryBook.deleteById(id);
    }
}