package com.example.booktrackerservice.commandHandlers.handlers;

import com.example.booktrackerservice.commandHandlers.commands.CreateBookTracker;
import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Status;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBookTrackerHandler implements CommandHandler<CreateBookTracker>{
    private final TrackerRepo trackerRepo;
    @Autowired
    public CreateBookTrackerHandler(TrackerRepo trackerRepo) {
        this.trackerRepo = trackerRepo;
    }

    @Override
    public void execute(CreateBookTracker command) {
        BookTracker bookTracker = new BookTracker();
        bookTracker.setBookId(command.getBookId());
        bookTracker.setBookStatus(Status.AVAILABLE);
        trackerRepo.save(bookTracker);
    }
}
