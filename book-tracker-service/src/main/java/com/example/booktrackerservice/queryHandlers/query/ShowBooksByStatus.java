package com.example.booktrackerservice.queryHandlers.query;


import com.example.booktrackerservice.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class ShowBooksByStatus extends BaseQuery {
    private Status status;
    public ShowBooksByStatus(Status status){
        this.status=status;
    }
    public Status getStatus() {
        return status;
    }
}
