package com.bookmanagementapi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.bookmanagementapi.dto.BookDto;
import com.bookmanagementapi.mapper.BookMapper;
import com.bookmanagementapi.repository.BookRepository;
import com.bookmanagementapi.service.BookService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagementapi.domain.Book;

@RestController
@RequestMapping("/api/books")
@Validated
@Slf4j
public class BookController {

    // Inject BookRepository, BookService, and BookMapper
    @Autowired
    BookRepository bookRepository;
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    // GET endpoint to retrieve all books
    @GetMapping
    List<Book> getAllBooks(){
        log.info("Getting all books");
        return bookRepository.findAll();
    }

    // GET endpoint to retrieve a book by ID
    @GetMapping(value="/{id}")
    ResponseEntity<?> getBookById(@PathVariable("id") @Min(1) Long id) {
        log.info("Getting book by Id: {}", id);
        Book book = bookService.getBookById(id);
        BookDto bookDto = bookMapper.toDto(book);
        return ResponseEntity.ok().body(bookDto);
    }

    // POST endpoint to create a new book
    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        log.info("Request for create book: {}", bookDto);
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookService.createBook(book);
        BookDto savedBookDto = bookMapper.toDto(savedBook);
        log.info("Book created: {}", savedBookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookDto);
    }

    // PUT endpoint to update an existing book by ID
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        log.info("Request for updating book: {}", bookDto);
        Book book = bookMapper.toEntity(bookDto);
        Book updatedBook = bookService.updateBook(id, book);
        BookDto updatedBookDto = bookMapper.toDto(updatedBook);
        log.info("Book updated: {}", updatedBookDto);
        return ResponseEntity.ok(updatedBookDto);
    }

    // DELETE endpoint to delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("Request for deleting book: {}", id);
        Book book = bookService.getBookById(id);
        bookService.deleteBook(id);
        log.info("Book deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }

}
