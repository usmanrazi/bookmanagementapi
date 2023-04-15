package com.bookmanagementapi.domain;

import javax.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    @Column(nullable = true)
    private String description;

    public Book(String title, String author, LocalDate publicationDate, String description) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.description = description;
    }
}
