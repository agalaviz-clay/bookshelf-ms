package com.mtatgc.bookshelfms.controller;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This controller accepts, handles, and responds to HTTP requests from endpoints that deal with books
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Book createBook(@Valid @RequestBody Book book) {
        // TODO: book service next,
        // book
        System.out.println("Book title name: " + book.getTitle());
        System.out.println("Received data");
        // book.setTitle("Andres' Revenge");
        return book;
        // how to make sure a field is required
    }

    @GetMapping
    public String helloWorld() {
        return "Hello Maidres";
    }
}
