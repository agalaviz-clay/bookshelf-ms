package com.mtatgc.bookshelfms.model;

import java.time.LocalDate;

public class Book {

    private long id;
    private String title;
    private String genre;
    private int authorId;
    private LocalDate publishedDate;

    // default constructor to be used by SpringBoot for bean creation
    public Book() {}

    public Book(long id, String title, String genre, int authorId, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
        this.publishedDate = publishedDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getAuthorId() {
        return authorId;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }



}
