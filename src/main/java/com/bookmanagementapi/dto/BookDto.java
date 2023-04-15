package com.bookmanagementapi.dto;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@ToString
public class BookDto {
    @Null(message = "ID should be NULL")
    private Long id;
    @NotEmpty(message = "Title is mandatory")
    private String title;
    @NotEmpty(message = "Author is mandatory")
    private String author;
    @NotNull(message = "Publication date is required")
    //@DateFormat
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate publicationDate;
    private String description;

    // constructor, getters and setters

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
