package com.bookmanagementapi;

import com.bookmanagementapi.domain.Book;
import com.bookmanagementapi.repository.BookRepository;
import com.bookmanagementapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Author 1", null, null));
        books.add(new Book("Book 2", "Author 2", null, null));
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        // Arrange
        Long id = 1L;
        Book book = new Book("Book 1", "Author 1", null, null);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        // Act
        Book result = bookService.getBookById(id);

        // Assert
        assertNotNull(result);
        assertEquals("Book 1", result.getTitle());
        assertEquals("Author 1", result.getAuthor());
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void testCreateBook() {
        // Arrange
        Book book = new Book("Book 1", "Author 1", null, null);
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Book result = bookService.createBook(book);

        // Assert
        assertNotNull(result);
        assertEquals("Book 1", result.getTitle());
        assertEquals("Author 1", result.getAuthor());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook() {
        // Arrange
        Long id = 1L;
        Book existingBook = new Book("Book 1", "Author 1", null, null);
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));

        Book updatedBook = new Book("Book 1 - Updated", "Author 1 - Updated", null, null);

        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        // Act
        Book result = bookService.updateBook(id, updatedBook);

        // Assert
        assertNotNull(result);
        assertEquals("Book 1 - Updated", result.getTitle());
        assertEquals("Author 1 - Updated", result.getAuthor());
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void testUpdateBook_NotFound() {
        // Arrange
        Long id = 1L;
        Book book = new Book("Test Title", "Test Author", LocalDate.now(), "Test Description");
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Book result = bookService.updateBook(id, book);

        // Assert
        assertNull(result);
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testDeleteBook() {
        // Arrange
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublicationDate(LocalDate.now());
        book.setDescription("Test Description");

        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        // Act
        bookService.deleteBook(id);

        // Assert
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(id);
    }

}