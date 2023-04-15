package com.bookmanagementapi.service;

import com.bookmanagementapi.domain.Book;
import com.bookmanagementapi.exceptions.ResourceNotFoundException;
import com.bookmanagementapi.repository.BookRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "booksCache")
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Book not found with id {}", id);
                    return new ResourceNotFoundException("Book not found with id " + id);
                });
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElse(null);

        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublicationDate(book.getPublicationDate());
            existingBook.setDescription(book.getDescription());

            return bookRepository.save(existingBook);
        }

        log.warn("Book not found with id {}", id);
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
