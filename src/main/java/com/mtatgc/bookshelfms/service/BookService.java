package com.mtatgc.bookshelfms.service;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BookService is where business logic/functional requirements are implemented.
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    public Book getBook(long id) {
       return bookRepository.findByBookId(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
