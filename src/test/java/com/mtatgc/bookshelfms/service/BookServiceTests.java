package com.mtatgc.bookshelfms.service;

import com.mtatgc.bookshelfms.model.Book;
import com.mtatgc.bookshelfms.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookService mockBookService;

    @Test
    void get_book_by_book_id() throws Exception {
        LocalDate date = LocalDate.of(2020, 1, 13);
        Book book = new Book(1L, "Book Title", "Horror", 123, date);

        when(mockBookRepository.findByBookId(anyLong()))
            .thenReturn(book);

        Book respBook = mockBookService.getBook(1L);

        assertEquals(book, respBook);
    }

    @Test
    void get_nonexistent_book_by_book_id() throws Exception {
        when(mockBookRepository.findByBookId(anyLong()))
            .thenReturn(null);

        Book respBook = mockBookService.getBook(1L);

        assertNull(respBook);
    }

    @Test
    void get_all_books() throws Exception {
        ArrayList<Book> list = new ArrayList<>();
        LocalDate date1 = LocalDate.of(2024, 1, 13);
        LocalDate date2 = LocalDate.of(2025, 2, 14);
        Book book1 = new Book(1L, "Title 1", "Horror", 123, date1);
        Book book2 = new Book(2L, "Title 2", "Mystery", 321, date2);
        list.add(book1);
        list.add(book2);

        when(mockBookRepository.findAll())
            .thenReturn(list);

        Iterable<Book> respList = mockBookService.getAllBooks();

        assertEquals(list, respList);
    }

    @Test
    void no_books_to_get() throws Exception {
        when(mockBookRepository.findAll())
            .thenReturn(Collections.emptyList());

        Iterable<Book> respList = mockBookService.getAllBooks();

        assertInstanceOf(Iterable.class, respList);
        assertFalse(respList.iterator().hasNext());
    }

    @Test
    void create_new_book() throws Exception {
        LocalDate date = LocalDate.of(2024, 1, 1);
        Book book = new Book(1L, "Title", "Horror", 123, date);

        when(mockBookRepository.save(any(Book.class)))
            .thenReturn(book);

        Book respBook = mockBookService.createBook(book);

        assertEquals(book, respBook);
    }

    @Test
    void save_book() throws Exception {
        LocalDate date = LocalDate.of(2024, 1, 13);
        Book book = new Book(1L, "Title", "Horror", 123, date);

        when(mockBookRepository.save(any(Book.class)))
            .thenReturn(book);

        Book respBook = mockBookService.saveBook(book);

        assertEquals(book, respBook);
    }

    @Test
    void delete_book_by_book_id() throws Exception {
        doNothing().when(mockBookRepository).deleteById(anyLong());

        mockBookService.deleteBook(1L);

        verify(mockBookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void delete_all_books() throws Exception {
        doNothing().when(mockBookRepository).deleteAll();

        mockBookService.deleteAllBooks();

        verify(mockBookRepository, times(1)).deleteAll();
    }
}
