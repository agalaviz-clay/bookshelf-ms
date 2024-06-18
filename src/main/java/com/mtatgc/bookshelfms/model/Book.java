package com.mtatgc.bookshelfms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import java.time.LocalDate;

@EnableAutoConfiguration
@Entity
public class Book {
    @Id
    @NotNull(message = "id is required")
    @Positive
    private Long id;
    @NotNull(message = "title is required")
    private String title;
    private String genre;
    @NotNull(message = "author_id is required")
    @JsonProperty("author_id")
    @Positive
    private Integer authorId;
    @NotNull(message = "published_date is required")
    @JsonProperty("published_date")
    private LocalDate publishedDate;

    // default constructor to be used by SpringBoot for bean creation
    public Book() {}

    public Book(Long id, String title, String genre, Integer authorId, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n" + "author_id: " + this.authorId + "\n" +"genre: " + this.genre +
                "\n" + "published_date: " + this.publishedDate + "\n" +"title: " + this.title;
    }
}
