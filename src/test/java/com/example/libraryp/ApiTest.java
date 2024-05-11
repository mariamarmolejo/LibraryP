package com.example.libraryp;

import com.example.libraryp.controllers.Api;
import com.example.libraryp.model.Book;
import com.example.libraryp.repository.IBookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Api.class)
public class ApiTest {

    @Autowired
    private MockMvc mockM;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IBookRepository bookRepository;

    @Test
    public void shouldReturnAllBooks() throws Exception {
        Book book = new Book("Don Quijote", "Gabriel G", 1967, "Realismo");
        List<Book> books = Arrays.asList(book);

        given(bookRepository.findAll()).willReturn(books);

        mockM.perform(get("/apiBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(book.getTitle())));
    }

    @Test
    public void shouldReturnBookById() throws Exception {
        Long id = 0L;
        Book book = new Book("Don Quijote", "Gabriel G", 1967, "Realismo");

        given(bookRepository.findById(id)).willReturn(Optional.of(book));

        mockM.perform(get("/apiBooks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId()), Long.class))
                .andExpect(jsonPath("$.title", is(book.getTitle())));
    }

    @Test
    public void shouldReturnNotFoundForInvalidBookId() throws Exception {
        Long id = 1L;

        given(bookRepository.findById(id)).willReturn(Optional.empty());

        mockM.perform(get("/apiBooks/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateNewBook() throws Exception {
        Book book = new Book("Don Quijote", "Gabriel G", 1967, "Realismo");

        given(bookRepository.save(any(Book.class))).willReturn(book);

        mockM.perform(post("/apiBooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(book.getTitle())));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Long id = 1L;
        Book oldBook = new Book("Don Quijote", "Gabriel G", 1967, "Realismo");
        Book updatedBook = new Book("Don Quijote de La Mancha", "Gabriel Garcia", 2024, "Terror");

        given(bookRepository.findById(id)).willReturn(Optional.of(oldBook));
        given(bookRepository.save(any(Book.class))).willReturn(updatedBook);

        mockM.perform(put("/apiBooks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(updatedBook.getTitle())))
                .andExpect(jsonPath("$.author", is(updatedBook.getAuthor())))
                .andExpect(jsonPath("$.publicationYear", is(updatedBook.getPublicationYear())))
                .andExpect(jsonPath("$.genre", is(updatedBook.getGenre())));
    }


    //Test para validar que el libro fue borrado con exito
    @Test
    public void shouldReturnNotFoundForInvalidBookUpdateId() throws Exception {
        Long id = 1L;
        Book updatedBook = new Book("New T", "New A", 2013, "New G");

        given(bookRepository.findById(id)).willReturn(Optional.empty());

        mockM.perform(put("/apiBooks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isNotFound());
    }

    //Test para validar que el libro fue borrado con exito
    @Test
    public void shouldDeleteBook() throws Exception {
        Long id = 1L;

        doNothing().when(bookRepository).deleteById(id);

        mockM.perform(delete("/apiBooks/{id}", id))
                .andExpect(status().isOk());

        verify(bookRepository).deleteById(id);
    }
}