package com.example.booktrackerservice.entities;

import com.example.booktrackerservice.entities.Dto.BaseTracker;
import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.time.LocalDate;
import java.util.Date;


@Entity
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class BookTracker  extends BaseTracker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long bookId;
    @Enumerated(EnumType.STRING)
    private Status bookStatus;
    private Date borrowDate;
    private Date returnDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setBookStatus(Status status) {
        this.bookStatus = status;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
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
}
