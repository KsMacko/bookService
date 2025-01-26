package com.example.booktrackerservice.controller;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.entities.Status;
import com.example.booktrackerservice.queryHandlers.dispatch.QueryBookTrackerDispatcher;
import com.example.booktrackerservice.queryHandlers.query.GetTrackerByBookId;
import com.example.booktrackerservice.queryHandlers.query.GetTrackerById;
import com.example.booktrackerservice.queryHandlers.query.ShowAllBookTrackers;
import com.example.booktrackerservice.queryHandlers.query.ShowFreeBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackerQueryController {
    private final QueryBookTrackerDispatcher queryBookTrackerDispatcher;

    @Autowired
    public TrackerQueryController(QueryBookTrackerDispatcher queryBookTrackerDispatcher) {
        this.queryBookTrackerDispatcher = queryBookTrackerDispatcher;
    }
    @GetMapping("/get_free_book_trackers")
    List<BookTrackerDto> getFreeBooks(){
        return queryBookTrackerDispatcher.dispatch(new ShowFreeBooks());
    }
    @GetMapping("/get_all_book_trackers")
    List<BookTrackerDto> getAllBooks(){
        return queryBookTrackerDispatcher.dispatch(new ShowAllBookTrackers());
    }
    @GetMapping("/get_tracker_byBook/{book_id}")
    List<BookTrackerDto> getTrackerByBookId(@PathVariable Long book_id){
        return queryBookTrackerDispatcher.dispatch(new GetTrackerByBookId(book_id));
    }
    @GetMapping("/get_tracker_byId/{id}")
    List<BookTrackerDto> getTrackerById(@PathVariable Long id){
        return queryBookTrackerDispatcher.dispatch(new GetTrackerById(id));
    }
}
