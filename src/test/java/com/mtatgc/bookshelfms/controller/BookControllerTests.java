package com.mtatgc.bookshelfms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTests {
    @MockBean
    private BookService mockBookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For serializing and deserializing JSON

    @Test
    void get_book_by_book_id() throws Exception {
        // arrange
        LocalDate date = LocalDate.of(2020, 1, 8);
        Book book = new Book(1L, "Hunger Games", "Action", 123, date);

        when(mockBookService.getBook(anyLong()))
            .thenReturn(book);

        // act
        ResultActions resp = mockMvc.perform(get("/books/1"));

        // assert
        resp.andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(book.getId()))
            .andExpect(jsonPath("$.title").value(book.getTitle()))
            .andExpect(jsonPath("$.genre").value(book.getGenre()))
            .andExpect(jsonPath("$.author_id").value(book.getAuthorId()));
    }

    @Test
    void get_nonexistent_book_by_book_id() throws Exception {
        // arrange
        when(mockBookService.getBook(anyLong()))
            .thenReturn(null);

        // act
        ResultActions resp = mockMvc.perform(get("/books/2"));

        // assert
        resp.andExpect(status().isNotFound());
    }

    @Test
    void get_all_books() throws Exception {
        // arrange
        LocalDate date1 = LocalDate.of(2020, 1, 8);
        LocalDate date2 = LocalDate.of(2021, 2, 13);
        Book book1 = new Book(1L, "Hunger Games", "Action", 123, date1);
        Book book2 = new Book(2L, "Starving Trials", "Horror", 45, date2);
        ArrayList<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(book1);
        listOfBooks.add(book2);

        when(mockBookService.getAllBooks())
            .thenReturn(listOfBooks);

        // act
        ResultActions resp = mockMvc.perform(get("/books"));

        // assert
        resp.andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(objectMapper.writeValueAsString(listOfBooks))); // Full JSON check
    }

    @Test
    void no_books_to_get() throws Exception {
        // arrange
        ArrayList<Book> emptyList = new ArrayList<>();

        when(mockBookService.getAllBooks())
            .thenReturn(emptyList);

        // act
        ResultActions resp = mockMvc.perform(get("/books"));

        // assert
        resp.andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json("[]"));
    }

    @Test
    void create_new_book() throws Exception {
        // arrange
        LocalDate date = LocalDate.of(2024, 6, 24);
        Book book = new Book(99L,"Book99", "Numbers", 3465, date);

        when(mockBookService.getBook(anyLong()))
            .thenReturn(null);

        when(mockBookService.createBook(any(Book.class)))
            .thenReturn(book);

        // act
        ResultActions resp = mockMvc.perform(
            post("/books")
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // assert
        resp.andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    void update_existing_book() throws Exception {
        // arrange
        LocalDate date = LocalDate.of(2024, 1, 13);
        Book originalBook = new Book(1L, "Original Name", "Horror", 32, date);

        Book updates = new Book();
        updates.setId(1L);
        updates.setTitle("New Name");
        updates.setGenre("Mystery");

        when(mockBookService.getBook(anyLong()))
            .thenReturn(originalBook);

        when(mockBookService.saveBook(any(Book.class)))
            .thenReturn(originalBook);

        // act
        ResultActions resp = mockMvc.perform(
            put("/books/1")
                .content(objectMapper.writeValueAsString(updates))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // assert
        resp.andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(originalBook.getId()))
            .andExpect(jsonPath("$.title").value(updates.getTitle()))
            .andExpect(jsonPath("$.genre").value(updates.getGenre()))
            .andExpect(jsonPath("$.author_id").value(originalBook.getAuthorId()));
    }

    @Test
    void update_nonexistent_book() throws Exception {
        // arrange
        Book updates = new Book();
        updates.setGenre("Action");
        when(mockBookService.getBook(anyLong()))
            .thenReturn(null);

        // act
        ResultActions resp = mockMvc.perform(
            put("/books/1")
                .content(objectMapper.writeValueAsString(updates))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // assert
        resp.andExpect(status().isNotFound());
    }

    @Test
    void update_existing_book_id_not_possible() throws Exception {
        // arrange
        LocalDate date = LocalDate.of(2024, 1, 13);
        Book original = new Book(1L, "Original Name", "Horror", 32, date);
        Book updates = new Book();
        updates.setId(3L);
        updates.setGenre("Romance");

        when(mockBookService.getBook(anyLong()))
            .thenReturn(original);

        // act
        ResultActions resp = mockMvc.perform(
            put("/books/1")
                .content(objectMapper.writeValueAsString(updates))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // assert
        resp.andExpect(status().isBadRequest())
            .andExpect(content().string("Mismatch ID between path and body"));
    }

    @Test
    void delete_book_by_id() throws Exception {
        // arrange
        doNothing().when(mockBookService).deleteBook(anyLong());

        // act
        ResultActions resp = mockMvc.perform(delete("/books/1"));

        // assert
        resp.andExpect(status().isNoContent());
    }

    @Test
    void delete_all_books() throws Exception {
        // arrange
        doNothing().when(mockBookService).deleteAllBooks();

        // act
        ResultActions resp = mockMvc.perform(delete("/books"));

        // assert
        resp.andExpect(status().isNoContent());
    }
}
