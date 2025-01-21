package com.example.booktrackerservice.commandHandlers.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class DeleteTrackerByBookId extends BaseCommand{
    private Long id;

    public Long getId() {
        return id;
    }

    public DeleteTrackerByBookId(Long id) {
        this.id = id;
    }
}
