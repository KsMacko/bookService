package com.example.booktrackerservice.service;

import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Status;
import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.repo.TrackerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final TrackerRepo trackerRepo;
    private final TransactionTemplate transactionTemplate;

    public void changeBookStatus(BookTrackerDto bookTrackerDto) {
        Optional<BookTracker> entity = trackerRepo.findById(bookTrackerDto.getId());
        entity.ifPresent(trackerEntity -> {
            if (bookTrackerDto.getBookStatus() != null) trackerEntity.setBookStatus(bookTrackerDto.getBookStatus());
            if (bookTrackerDto.getBorrowDate() != null) trackerEntity.setBorrowDate(bookTrackerDto.getBorrowDate());
            if (bookTrackerDto.getReturnDate() != null) trackerEntity.setReturnDate(bookTrackerDto.getReturnDate());
            trackerRepo.save(trackerEntity);
        });
    }
    public void createBookTracker(Long bookId) {
        BookTracker bookTracker = new BookTracker();
        bookTracker.setBookId(bookId);
        bookTracker.setBookStatus(Status.AVAILABLE);
        trackerRepo.save(bookTracker);
    }
    public void deleteTrackerByBookId(Long id) {
        transactionTemplate.execute(status -> {
            trackerRepo.deleteByBookId(id);
            return null;
        });

    }
}
