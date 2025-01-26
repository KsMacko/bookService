package com.example.booktrackerservice.queryHandlers.handler;

import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.queryHandlers.query.GetTrackerById;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetTrackerByIdHandler implements QueryHandler<GetTrackerById>{
    private final TrackerRepo trackerRepo;
    private final TrackerHandler trackerHandler;

    @Autowired
    public GetTrackerByIdHandler(TrackerRepo trackerRepo, TrackerHandler trackerHandler) {
        this.trackerRepo = trackerRepo;
        this.trackerHandler = trackerHandler;
    }

    @Override
    public List<BookTrackerDto> execute(GetTrackerById query) {
        List<BookTrackerDto> dtos = new ArrayList<>();
        Optional<BookTracker> bookTracker = trackerRepo.findById(query.getId());
        bookTracker.ifPresent(trackerEntity->dtos.add(trackerHandler.handleToDto(trackerEntity)));
        return dtos;
    }
}
