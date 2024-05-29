package com.mtatgc.bookshelfms.controller;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This controller accepts, handles, and responds to HTTP requests from endpoints that deal with books
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        // TODO validation
        Book updatedBook = bookService.createBook(book);
        System.out.println("Book title name: " + updatedBook.getTitle());
        System.out.println("Received data");
        return updatedBook;
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    // TODO @PutMapping endpoint
    // TODO @DeleteMapping endpoint
}
