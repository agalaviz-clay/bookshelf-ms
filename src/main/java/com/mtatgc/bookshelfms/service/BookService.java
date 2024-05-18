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
    @Autowired
    BookRepository bookRepository;
    public Book createBook(Book book) {
        return bookRepository.saveBook(book);
    }
}
