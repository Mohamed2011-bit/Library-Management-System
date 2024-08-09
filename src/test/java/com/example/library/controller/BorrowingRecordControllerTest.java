package com.example.library.controller;

import com.example.library.model.BorrowingRecord;
import com.example.library.service.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Test
    void testBorrowBook() {
        BorrowingRecord record = new BorrowingRecord();
        when(borrowingRecordService.borrowBook(1L, 1L)).thenReturn(record);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(1L, 1L);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(record, response.getBody());
    }

    @Test
    void testReturnBook() {
        BorrowingRecord record = new BorrowingRecord();
        when(borrowingRecordService.returnBook(1L, 1L)).thenReturn(record);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(record, response.getBody());
    }
}
