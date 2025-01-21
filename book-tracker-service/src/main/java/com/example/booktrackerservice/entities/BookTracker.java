package com.example.booktrackerservice.entities;

import com.example.booktrackerservice.entities.Dto.BaseTracker;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@Entity
public class BookTracker  extends BaseTracker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long bookId;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean deleted;

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
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
}
