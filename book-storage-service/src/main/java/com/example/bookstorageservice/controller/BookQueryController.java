package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.entity.dto.BookDetailsDto;
import com.example.bookstorageservice.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookQueryController {
    private final QueryService queryService;


    @GetMapping("/find_all")
    public ResponseEntity<List<BookDetailsDto>> findAll() {
        return ResponseEntity.ok(queryService.findAllBooks());
    }

    @GetMapping("/search_by_isbn")
    public ResponseEntity<List<BookDetailsDto>> searchByISBN(@RequestParam("isbn") String isbn) {
        return ResponseEntity.ok(queryService.findBookByIsbn(isbn));
    }

    @GetMapping("/search_by_id")
    public ResponseEntity<BookDetailsDto> searchById(@RequestParam("id") Long bookId) {
        return ResponseEntity.ok(queryService.findBookById(bookId));
    }

    @GetMapping("/show_free")
    public ResponseEntity<List<BookDetailsDto>> showFreeBooks() {
        return ResponseEntity.ok(queryService.getFreeBooks());
    }
}
