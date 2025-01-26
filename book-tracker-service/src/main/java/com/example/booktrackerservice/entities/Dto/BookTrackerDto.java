package com.example.booktrackerservice.entities.Dto;

import com.example.booktrackerservice.entities.Status;

import java.time.LocalDate;
import java.util.Date;

public class BookTrackerDto {
    private Long id;
    private Long bookId;
    private Status bookStatus;
    private Date borrowDate;
    private Date returnDate;
    public BookTrackerDto() {
    }
    public BookTrackerDto(Long id, Long bookId, Status bookStatus, Date borrowDate, Date returnDate) {
        this.id=id;
        this.bookId = bookId;
        this.bookStatus = bookStatus;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Status getBookStatus() {
        return bookStatus;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookStatus(Status bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
