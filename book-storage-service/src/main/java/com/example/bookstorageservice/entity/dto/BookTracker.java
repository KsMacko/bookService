package com.example.bookstorageservice.entity.dto;

import com.example.bookstorageservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTracker {
    private Long id;
    private Long bookId;
    private Status bookStatus;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date borrowDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date returnDate;
}
