package com.mtatgc.bookshelfms.controller;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.repository.BookRepository;
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
        // TODO: Our system should assign bookID, it should not be provided as information in JSON
        // TODO: I can currently create a new book with ID 999
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

    @GetMapping("/all")
    public ResponseEntity<String> getAllBooks() {
        // also pathing above could just be empty since the other get uses
        // path of /{id}
        // or is there a better name for the path?
        Iterable<Book> listOfBooks = bookService.getAllBooks();

        // for validation what do I want to do if there is no books
        // in the database is it okay to return empty or null whatever it
        // returns?

        // do I really want .toString in that format
        // or something else. like does it do .toString on each iterable
        // aka each book in the list? or what
        return ResponseEntity.ok(listOfBooks.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        // should I check if id exists before trying to delete
        // I believe that .deleteBook that calls .deleteById() deletes if its found
        // and does nothing if it's not found
        bookService.deleteBook(id);
        // if I do care maybe I can see if id exists that the body is that
        // it was successfully deleted

        // else if not available just say in the body that the ID did not
        // exists so it was not deleted I don't think that needs to be a
        // .badRequest().body("some message")
        return ResponseEntity.ok("Book is deleted");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllBooks() {
        bookService.deleteAllBooks();
        return ResponseEntity.ok("Books are deleted");
    }
}
