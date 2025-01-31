package com.example.booktrackerservice.entities.dto;

import com.example.booktrackerservice.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTrackerDto {
    private Long id;
    private Long bookId;
    private Status bookStatus;
    private Date borrowDate;
    private Date returnDate;
}
