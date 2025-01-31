package com.example.booktrackerservice.service;

import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.repo.TrackerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final TrackerRepo trackerRepo;
    private final TrackerHandler trackerHandler;

    public List<BookTrackerDto> getTrackerByBookId(Long bookId) {
        return trackerRepo.findAllByBookIdOrderByBorrowDateDesc(bookId)
                .stream().map(trackerHandler::handleToDto).toList();
    }

    public List<BookTrackerDto> showAllBookTrackers() {
        return trackerRepo.findAllByOrderByBorrowDateDesc().stream().map(trackerHandler::handleToDto).toList();
    }

    public List<BookTrackerDto> showFreeBooks() {
        return trackerRepo.findFreeBooks()
                .stream().map(trackerHandler::handleToDto).toList();
    }
}
