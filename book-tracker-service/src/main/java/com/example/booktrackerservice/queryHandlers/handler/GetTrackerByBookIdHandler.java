package com.example.booktrackerservice.queryHandlers.handler;

import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.queryHandlers.query.GetTrackerByBookId;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetTrackerByBookIdHandler implements QueryHandler<GetTrackerByBookId>{
    private final TrackerRepo trackerRepo;
    private final TrackerHandler trackerHandler;

    @Autowired
    public GetTrackerByBookIdHandler(TrackerRepo trackerRepo, TrackerHandler trackerHandler) {
        this.trackerRepo = trackerRepo;
        this.trackerHandler = trackerHandler;
    }

    @Override
    public List<BookTrackerDto> execute(GetTrackerByBookId query) {
        return trackerRepo.findAllByBookIdOrderByBorrowDateDesc(query.getBookId())
                .stream().map(trackerHandler::handleToDto).toList();
    }
}
