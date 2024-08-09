package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(new Book()));
        List<Book> books = bookController.getAllBooks();
        assertEquals(1, books.size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));
        ResponseEntity<Book> response = bookController.getBookById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        when(bookService.saveBook(book)).thenReturn(book);
        ResponseEntity<Book> response = bookController.addBook(book);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
    }
}
