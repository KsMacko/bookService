package com.example.booktrackerservice.commandHandlers.handlers;

import com.example.booktrackerservice.commandHandlers.commands.DeleteTrackerByBookId;
import com.example.booktrackerservice.repo.TrackerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class DeleteTrackerHandler implements CommandHandler<DeleteTrackerByBookId>{
    private final TrackerRepo trackerRepo;

    @Autowired private TransactionTemplate transactionTemplate;

    @Autowired
    public DeleteTrackerHandler(TrackerRepo trackerRepo) {
        this.trackerRepo = trackerRepo;
    }


    @Override
    public void execute(DeleteTrackerByBookId command) {
        transactionTemplate.execute(status -> {
            trackerRepo.deleteByBookId(command.getId());
            return null;
        });

    }
}
