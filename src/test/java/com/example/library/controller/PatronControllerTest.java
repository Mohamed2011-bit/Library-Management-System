package com.example.library.controller;

import com.example.library.model.Patron;
import com.example.library.service.PatronService;
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

class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @Test
    void testGetAllPatrons() {
        when(patronService.getAllPatrons()).thenReturn(Collections.singletonList(new Patron()));
        List<Patron> patrons = patronController.getAllPatrons();
        assertEquals(1, patrons.size());
    }

    @Test
    void testGetPatronById() {
        Patron patron = new Patron();
        when(patronService.getPatronById(1L)).thenReturn(Optional.of(patron));
        ResponseEntity<Patron> response = patronController.getPatronById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    void testAddPatron() {
        Patron patron = new Patron();
        when(patronService.savePatron(patron)).thenReturn(patron);
        ResponseEntity<Patron> response = patronController.addPatron(patron);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }
}
