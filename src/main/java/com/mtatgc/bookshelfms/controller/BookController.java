package com.mtatgc.bookshelfms.controller;

import com.mtatgc.bookshelfms.model.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller accepts, handles, and responds to HTTP requests from endpoints that deal with books
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Book createBook(@RequestBody Book book) {
        // TODO: book service next, also explain @RequestBody more and @JsonProperty
    }
}
