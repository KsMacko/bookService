package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.BaseDto;
import com.example.bookstorageservice.entity.DTO.BookTracker;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.queryHandlers.query.FindBookTrackerById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindBookTrackerByIdHandler implements QueryHandler<FindBookTrackerById>{
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;
    @Autowired
    public FindBookTrackerByIdHandler(BookTrackerServiceFeignClient bookTrackerServiceFeignClient) {
        this.bookTrackerServiceFeignClient = bookTrackerServiceFeignClient;
    }

    @Override
    public List<BookTracker> execute(FindBookTrackerById query) {
        return bookTrackerServiceFeignClient.getTrackerById(query.getId());
    }
}
