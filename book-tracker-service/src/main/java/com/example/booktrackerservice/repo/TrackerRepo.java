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
    @Modifying
    @Query("UPDATE BookTracker SET deleted=true WHERE bookId= :bookId")
    void deleteByBookId(@Param("bookId") Long id);
    @Modifying
    @Query("UPDATE BookTracker SET status= :status WHERE id= :id")
    void updateBookTrackerByBookId(@Param("id")Long id, @Param("status") Status status);
    List<BookTracker> findAllByStatus(Status status);
    BookTracker findByBookId(Long id);
}
