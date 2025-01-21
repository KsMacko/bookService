package com.example.booktrackerservice.commandHandlers.commands;

import com.example.booktrackerservice.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


public class ChangeBookStatus extends BaseCommand{
    private Status status;
    private Long id;

    public ChangeBookStatus(Status status, Long id) {
        this.status = status;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
