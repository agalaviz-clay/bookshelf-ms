package com.mtatgc.bookshelfms.controller;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{id}")
    public ResponseEntity<String> getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(book.toString());
    }

    @GetMapping()
    public ResponseEntity<String> getAllBooks() {
        Iterable<Book> listOfBooks = bookService.getAllBooks();
        return ResponseEntity.ok(listOfBooks.toString());
    }

    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody Book book) {
        if (bookService.getBook(book.getId()) != null) {
            return ResponseEntity.badRequest().body("This ID already exists");
        }
        Book updatedBook = bookService.createBook(book);
        return ResponseEntity.ok(updatedBook.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book originalBook = bookService.getBook(id);
        if (originalBook == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        return ResponseEntity.ok(originalBook.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllBooks() {
        bookService.deleteAllBooks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
