package com.example.booktrackerservice.queryHandlers.handler;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.queryHandlers.query.ShowAllBookTrackers;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllBookTrackersHandler implements QueryHandler<ShowAllBookTrackers>{
    private final TrackerRepo trackerRepo;
    private final TrackerHandler trackerHandler;

    @Autowired
    public ShowAllBookTrackersHandler(TrackerRepo trackerRepo, TrackerHandler trackerHandler) {
        this.trackerRepo = trackerRepo;
        this.trackerHandler = trackerHandler;
    }

    @Override
    public List<BookTrackerDto> execute(ShowAllBookTrackers query) {
        return trackerRepo.findAllByOrderByBorrowDateDesc().stream().map(trackerHandler::handleToDto).toList();
    }
}
