package com.example.bookstorageservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SoftDelete
public class BookEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;

    public Long getId() {
        return id;
    }
}
