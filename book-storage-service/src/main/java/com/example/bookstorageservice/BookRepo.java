package com.example.bookstorageservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {
    void deleteById(Long id);
    BookEntity findAllByIsbn(String isbn);

}
