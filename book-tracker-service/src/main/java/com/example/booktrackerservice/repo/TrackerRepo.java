package com.example.booktrackerservice.repo;

import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepo extends JpaRepository<BookTracker, Long> {
    void deleteByBookId(Long id);
    @Modifying
    @Query("UPDATE BookTracker SET bookStatus= :status WHERE id= :id")
    void updateBookTrackerById(@Param("id")Long id, @Param("status") Status status);
    List<BookTracker> findAllByBookIdOrderByBorrowDateDesc(Long bookId);
    List<BookTracker> findAllByOrderByBorrowDateDesc();
    @Query("SELECT bt FROM BookTracker bt WHERE bt.bookStatus = 'AVAILABLE'")
    List<BookTracker> findFreeBooks();
}
