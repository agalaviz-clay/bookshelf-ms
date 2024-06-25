package com.mtatgc.bookshelfms.repository;

import com.mtatgc.bookshelfms.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT * FROM book WHERE id = ?1", nativeQuery = true)
    Book findByBookId(Long id);
}
