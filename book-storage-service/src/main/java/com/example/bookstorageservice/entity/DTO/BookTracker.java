package com.example.bookstorageservice.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTracker extends BaseDto{
    private Long id;
    private Long bookId;
    private Status bookStatus;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date borrowDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date returnDate;
    private boolean deleted;
}
