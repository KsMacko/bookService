package com.example.booktrackerservice.queryHandlers.dispatch;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.queryHandlers.query.BaseQuery;

import java.util.List;

public interface QueryDispatcher {
    <U extends BookTrackerDto> List<U> dispatch(BaseQuery baseQuery);
}
