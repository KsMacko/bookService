package com.example.booktrackerservice.commandHandlers.handlers;

import com.example.booktrackerservice.commandHandlers.commands.DeleteTrackerByBookId;
import com.example.booktrackerservice.repo.TrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTrackerHandler implements CommandHandler<DeleteTrackerByBookId>{
    private final TrackerRepo trackerRepo;

    @Autowired
    public DeleteTrackerHandler(TrackerRepo trackerRepo) {
        this.trackerRepo = trackerRepo;
    }

    @Override
    public void execute(DeleteTrackerByBookId command) {
        trackerRepo.deleteByBookId(command.getId());
    }
}
