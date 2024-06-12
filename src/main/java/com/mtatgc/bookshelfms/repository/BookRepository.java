package com.mtatgc.bookshelfms.repository;

import com.mtatgc.bookshelfms.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
