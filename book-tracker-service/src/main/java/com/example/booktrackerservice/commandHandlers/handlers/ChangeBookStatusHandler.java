package com.example.booktrackerservice.commandHandlers.handlers;

import com.example.booktrackerservice.commandHandlers.commands.ChangeBookStatus;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeBookStatusHandler implements CommandHandler<ChangeBookStatus>{
    private final TrackerRepo trackerRepo;

    @Autowired
    public ChangeBookStatusHandler(TrackerRepo trackerRepo) {
        this.trackerRepo = trackerRepo;
    }

    @Override
    public void execute(ChangeBookStatus command) {
        trackerRepo.updateBookTrackerByBookId(command.getId(), command.getStatus());
    }
}
