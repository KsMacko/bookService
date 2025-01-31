package com.example.bookstorageservice.repo;

import com.example.bookstorageservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByIsbn(String isbn);

}
