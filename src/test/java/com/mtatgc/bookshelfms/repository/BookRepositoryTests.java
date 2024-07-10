package com.mtatgc.bookshelfms.repository;

import com.mtatgc.bookshelfms.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class BookRepositoryTests {
    @Autowired
    BookRepository bookRepository;

    @Test
    void find_book_by_book_id() throws Exception {
        LocalDate date = LocalDate.of(2024, 1, 13);
        Book book = new Book(1L, "Title", "Horror", 123, date);
        bookRepository.save(book);

        Book respBook = bookRepository.findByBookId(book.getId());

        Assertions.assertNotNull(respBook);
        Assertions.assertEquals(book.getId(), respBook.getId());
        Assertions.assertEquals(book.getTitle(), respBook.getTitle());
        Assertions.assertEquals(book.getGenre(), respBook.getGenre());
        Assertions.assertEquals(book.getAuthorId(), respBook.getAuthorId());
        Assertions.assertEquals(book.getPublishedDate(), respBook.getPublishedDate());
    }

    @Test
    void no_book_to_find_by_book_id() throws Exception {
        Book respBook = bookRepository.findByBookId(1L);

        Assertions.assertNull(respBook);
    }
}
