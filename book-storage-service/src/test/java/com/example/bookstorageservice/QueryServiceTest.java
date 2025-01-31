package com.example.bookstorageservice;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.BookHandler;
import com.example.bookstorageservice.entity.dto.BookDetailsDto;
import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.feignclients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import com.example.bookstorageservice.service.QueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class QueryServiceTest {
    @Mock
    private BookRepo bookRepo;

    @Mock
    private BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @InjectMocks
    private QueryService queryService;
    @Mock
    private BookHandler bookHandler;

    @Test
    public void testFindAllBooks() {
        when(bookRepo.findAll()).thenReturn(Collections.emptyList());
        when(bookTrackerServiceFeignClient.getAllBooks()).thenReturn(Collections.emptyList());

        List<BookDetailsDto> result = queryService.findAllBooks();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookRepo, times(1)).findAll();
        verify(bookTrackerServiceFeignClient, times(1)).getAllBooks();
    }


    @Test
    public void testFindBookByIsbn() {
        String isbn = "example";
        when(bookRepo.findAllByIsbn(isbn)).thenReturn(Collections.emptyList());
        when(bookTrackerServiceFeignClient.getAllBooks()).thenReturn(Collections.emptyList());

        List<BookDetailsDto> result = queryService.findBookByIsbn(isbn);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookRepo, times(1)).findAllByIsbn(isbn);
        verify(bookTrackerServiceFeignClient, times(1)).getAllBooks();
    }
    @Test
    public void testFindBookById() {
        Long bookId = 1L;
        BookEntity bookEntity = new BookEntity(bookId, "isbn", "name", "genre", "description", "author");
        BookDto bookDto = new BookDto(bookId, "isbn", "name", "genre", "description", "author");

        when(bookRepo.findById(bookId)).thenReturn(Optional.of(bookEntity));
        when(bookHandler.handleToDto(bookEntity)).thenReturn(bookDto);
        when(bookTrackerServiceFeignClient.getTrackerByBookId(bookId)).thenReturn(Collections.emptyList());

        BookDetailsDto result = queryService.findBookById(bookId);

        assertNotNull(result);
        verify(bookRepo, times(1)).findById(bookId);
        verify(bookHandler, times(1)).handleToDto(bookEntity);
        verify(bookTrackerServiceFeignClient, times(1)).getTrackerByBookId(bookId);
    }
    @Test
    public void testGetFreeBooks() {
        when(bookTrackerServiceFeignClient.getFreeBooks()).thenReturn(Collections.emptyList());

        List<BookDetailsDto> result = queryService.getFreeBooks();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookTrackerServiceFeignClient, times(1)).getFreeBooks();
    }


}
