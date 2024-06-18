package com.mtatgc.bookshelfms.controller;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * This controller accepts, handles, and responds to HTTP requests from endpoints that deal with books
 */
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    // Type used to be Book
    public ResponseEntity<String> createBook(@Valid @RequestBody Book book) {
        // TODO: Validation
        if (bookService.getBook(book.getId()) != null) {
            // book with this ID already exists
            return ResponseEntity.badRequest().body("This ID already exists");
        }

        Book updatedBook = bookService.createBook(book);
        return ResponseEntity.ok(updatedBook.toString());
        // System.out.println("Book title name: " + updatedBook.getTitle());
        // System.out.println("Received data");
        // return updatedBook;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        if (book == null) {
            return ResponseEntity.badRequest().body("ID does not exists");
        }

        return ResponseEntity.ok(book.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book originalBook = bookService.getBook(id);
        if (originalBook == null) {
            return ResponseEntity.badRequest().body("ID does not exists");
        }

        if (!id.equals(book.getId())) {
            return ResponseEntity.badRequest().body("Mismatch ID between path and body");
        }

        if (book.getTitle() != null) {
            originalBook.setTitle(book.getTitle());
        }

        if (book.getGenre() != null) {
            originalBook.setGenre(book.getGenre());
        }

        if (book.getAuthorId() != null) {
            originalBook.setAuthorId(book.getAuthorId());
        }

        if (book.getPublishedDate() != null) {
            originalBook.setPublishedDate(book.getPublishedDate());
        }

        bookService.saveBook(originalBook);

//        return ResponseEntity.ok().body(originalBook.toString());
        return ResponseEntity.ok(originalBook.toString());
    }


    // TODO: Create get endpoint for getting a list of books
    // TODO: Create delete endpoint for deleting all books

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
