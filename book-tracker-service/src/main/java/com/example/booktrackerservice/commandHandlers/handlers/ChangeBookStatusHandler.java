package com.example.booktrackerservice.commandHandlers.handlers;

import com.example.booktrackerservice.commandHandlers.commands.ChangeBookStatus;
import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.TrackerHandler;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeBookStatusHandler implements CommandHandler<ChangeBookStatus>{
    private final TrackerRepo trackerRepo;
    private final TrackerHandler trackerHandler;

    @Autowired
    public ChangeBookStatusHandler(TrackerRepo trackerRepo, TrackerHandler trackerHandler) {
        this.trackerRepo = trackerRepo;
        this.trackerHandler = trackerHandler;
    }

    @Override
    public void execute(ChangeBookStatus command) {
        Optional<BookTracker> entity = trackerRepo.findById(command.getBookTrackerDto().getId());
        entity.ifPresent(trackerEntity->{
            BookTrackerDto gotTracker = command.getBookTrackerDto();
            if(gotTracker.getBookStatus()!=null) trackerEntity.setBookStatus(gotTracker.getBookStatus());
            if(gotTracker.getBorrowDate()!=null) trackerEntity.setBorrowDate(gotTracker.getBorrowDate());
            if(gotTracker.getReturnDate()!=null) trackerEntity.setReturnDate(gotTracker.getReturnDate());
            trackerRepo.save(trackerEntity);
        });


    }
}
