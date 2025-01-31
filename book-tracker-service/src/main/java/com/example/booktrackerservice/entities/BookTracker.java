package com.example.booktrackerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.util.Date;


@Entity
@SoftDelete(strategy = SoftDeleteType.DELETED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    @Enumerated(EnumType.STRING)
    private Status bookStatus;
    private Date borrowDate;
    private Date returnDate;
}
