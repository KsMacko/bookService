package com.example.bookstorageservice;

import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.BookHandler;
import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.feignclients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import com.example.bookstorageservice.service.CommandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CommandServiceTest {
    @Mock
    private BookRepo bookRepo;

    @Mock
    private BookHandler bookHandler;

    @Mock
    private BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @InjectMocks
    private CommandService commandService;

    @Test
    public void testCreateNewTracker() {
        Long bookId = 1L;
        doNothing().when(bookTrackerServiceFeignClient).createBookTracker(bookId);

        commandService.createNewTracker(bookId);

        verify(bookTrackerServiceFeignClient, times(1)).createBookTracker(bookId);
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;
        doNothing().when(bookTrackerServiceFeignClient).deleteBookTracker(bookId);
        doNothing().when(bookRepo).deleteById(bookId);

        commandService.deleteBook(bookId);

        verify(bookRepo, times(1)).deleteById(bookId);
        verify(bookTrackerServiceFeignClient, times(1)).deleteBookTracker(bookId);
    }

    @Test
    public void testCreateNewBook() {
        BookDto bookDto = new BookDto(null, "example", "example", "example", "example", "example");
        BookEntity bookEntity = new BookEntity(1L, "example", "example", "example", "example", "example");
        when(bookHandler.handleToEntity(bookDto)).thenReturn(bookEntity);
        when(bookRepo.save(bookEntity)).thenReturn(bookEntity);

        commandService.saveBook(bookDto);

        verify(bookRepo, times(1)).save(bookEntity);
        verify(bookTrackerServiceFeignClient, times(1)).createBookTracker(bookEntity.getId());
    }
    @Test
    public void testUpdateBook() {
        BookDto bookDto = new BookDto(1L, "updated", "updated", "updated", "updated", "updated");
        BookEntity bookEntity = new BookEntity(1L, "example", "example", "example", "example", "example");

        when(bookRepo.findById(anyLong())).thenReturn(Optional.of(bookEntity));
        when(bookRepo.save(bookEntity)).thenReturn(bookEntity);

        commandService.saveBook(bookDto);

        verify(bookRepo, times(1)).save(bookEntity);
        verify(bookTrackerServiceFeignClient, times(0)).createBookTracker(bookEntity.getId());
    }


}
