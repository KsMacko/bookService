package com.example.booktrackerservice.queryHandlers.handler;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.queryHandlers.query.ShowFreeBooks;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowFreeBooksHandler implements QueryHandler<ShowFreeBooks>{
    private final TrackerRepo trackerRepo;
    private final TrackerHandler trackerHandler;

    @Autowired
    public ShowFreeBooksHandler(TrackerRepo trackerRepo, TrackerHandler trackerHandler) {
        this.trackerRepo = trackerRepo;
        this.trackerHandler = trackerHandler;
    }

    @Override
    public List<BookTrackerDto> execute(ShowFreeBooks query) {
        return  trackerRepo.findFreeBooks()
                .stream().map(trackerHandler::handleToDto).toList();
    }
}
