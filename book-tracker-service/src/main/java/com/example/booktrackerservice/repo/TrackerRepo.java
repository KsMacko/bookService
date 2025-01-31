package com.example.booktrackerservice.repo;

import com.example.booktrackerservice.entities.BookTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepo extends JpaRepository<BookTracker, Long> {
    void deleteByBookId(Long id);

    List<BookTracker> findAllByBookIdOrderByBorrowDateDesc(Long bookId);

    List<BookTracker> findAllByOrderByBorrowDateDesc();

    @Query("SELECT bt FROM BookTracker bt WHERE bt.bookStatus = 'AVAILABLE'")
    List<BookTracker> findFreeBooks();
}
