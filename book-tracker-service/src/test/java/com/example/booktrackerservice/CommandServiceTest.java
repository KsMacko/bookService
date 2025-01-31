package com.example.booktrackerservice;

import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Status;
import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.repo.TrackerRepo;
import com.example.booktrackerservice.service.CommandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandServiceTest {
    @Mock
    private TrackerRepo trackerRepo;
    @Mock
    private TransactionTemplate transactionTemplate;

    @InjectMocks
    private CommandService commandService;

    @Test
    public void testChangeBookStatus() {
        BookTrackerDto bookTrackerDto = new BookTrackerDto(1L, 1L, Status.BORROWED, new Date(), new Date());
        BookTracker bookTracker = new BookTracker(1L, 1L, Status.AVAILABLE, new Date(), new Date());

        when(trackerRepo.findById(bookTrackerDto.getId())).thenReturn(Optional.of(bookTracker));

        commandService.changeBookStatus(bookTrackerDto);

        verify(trackerRepo, times(1)).findById(bookTrackerDto.getId());
        verify(trackerRepo, times(1)).save(bookTracker);
        assertEquals(Status.BORROWED, bookTracker.getBookStatus());
    }

    @Test
    public void testCreateBookTracker() {
        Long bookId = 1L;

        commandService.createBookTracker(bookId);

        ArgumentCaptor<BookTracker> bookTrackerCaptor = ArgumentCaptor.forClass(BookTracker.class);
        verify(trackerRepo, times(1)).save(bookTrackerCaptor.capture());

        BookTracker savedBookTracker = bookTrackerCaptor.getValue();
        assertEquals(bookId, savedBookTracker.getBookId());
        assertEquals(Status.AVAILABLE, savedBookTracker.getBookStatus());
    }

    @Test
    public void testDeleteTrackerByBookId() {
        Long bookId = 1L;
        when(transactionTemplate.execute(any())).thenAnswer(invocation -> {
            TransactionCallback<?> callback = invocation.getArgument(0);
            return callback.doInTransaction(null);
        });
        commandService.deleteTrackerByBookId(bookId);

        verify(trackerRepo, times(1)).deleteByBookId(bookId);
    }
}
