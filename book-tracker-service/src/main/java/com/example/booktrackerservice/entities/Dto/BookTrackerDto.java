package com.example.booktrackerservice.entities.Dto;

import com.example.booktrackerservice.entities.Status;

import java.time.LocalDate;

public class BookTrackerDto {
    private Long bookId;
    private Status status;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean deleted;
    public BookTrackerDto() {
    }
    public BookTrackerDto(Long bookId, Status status, LocalDate borrowDate, LocalDate returnDate, boolean deleted) {
        this.bookId = bookId;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.deleted = deleted;
    }

    public Long getBookId() {
        return bookId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
