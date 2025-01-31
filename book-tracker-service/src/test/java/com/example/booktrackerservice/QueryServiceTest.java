package com.example.booktrackerservice;

import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Status;
import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.repo.TrackerRepo;
import com.example.booktrackerservice.service.QueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QueryServiceTest {
    @Mock
    private TrackerRepo trackerRepo;

    @Mock
    private TrackerHandler trackerHandler;

    @InjectMocks
    private QueryService queryService;

    @Test
    public void testGetTrackerByBookId() {
        Long bookId = 1L;
        BookTracker bookTracker = new BookTracker(1L, bookId, Status.AVAILABLE, new Date(), new Date());
        BookTrackerDto bookTrackerDto = new BookTrackerDto(1L, bookId, Status.AVAILABLE, new Date(), new Date());

        when(trackerRepo.findAllByBookIdOrderByBorrowDateDesc(bookId)).thenReturn(Collections.singletonList(bookTracker));
        when(trackerHandler.handleToDto(bookTracker)).thenReturn(bookTrackerDto);

        List<BookTrackerDto> result = queryService.getTrackerByBookId(bookId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookTrackerDto, result.get(0));
        verify(trackerRepo, times(1)).findAllByBookIdOrderByBorrowDateDesc(bookId);
        verify(trackerHandler, times(1)).handleToDto(bookTracker);
    }

    @Test
    public void testShowAllBookTrackers() {
        BookTracker bookTracker = new BookTracker(1L, 1L, Status.AVAILABLE, new Date(), new Date());
        BookTrackerDto bookTrackerDto = new BookTrackerDto(1L, 1L, Status.AVAILABLE, new Date(), new Date());

        when(trackerRepo.findAllByOrderByBorrowDateDesc()).thenReturn(Collections.singletonList(bookTracker));
        when(trackerHandler.handleToDto(bookTracker)).thenReturn(bookTrackerDto);

        List<BookTrackerDto> result = queryService.showAllBookTrackers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookTrackerDto, result.get(0));
        verify(trackerRepo, times(1)).findAllByOrderByBorrowDateDesc();
        verify(trackerHandler, times(1)).handleToDto(bookTracker);
    }

    @Test
    public void testShowFreeBooks() {
        BookTracker bookTracker = new BookTracker(1L, 1L, Status.AVAILABLE, new Date(), new Date());
        BookTrackerDto bookTrackerDto = new BookTrackerDto(1L, 1L, Status.AVAILABLE, new Date(), new Date());

        when(trackerRepo.findFreeBooks()).thenReturn(Collections.singletonList(bookTracker));
        when(trackerHandler.handleToDto(bookTracker)).thenReturn(bookTrackerDto);

        List<BookTrackerDto> result = queryService.showFreeBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookTrackerDto, result.get(0));
        verify(trackerRepo, times(1)).findFreeBooks();
        verify(trackerHandler, times(1)).handleToDto(bookTracker);
    }
}
