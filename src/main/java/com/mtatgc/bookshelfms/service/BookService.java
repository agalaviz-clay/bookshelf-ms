package com.mtatgc.bookshelfms.service;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * BookService is where business logic/functional requirements are implemented.
 */
@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
