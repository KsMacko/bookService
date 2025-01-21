package com.example.booktrackerservice.queryHandlers.handler;
import com.example.booktrackerservice.entities.BookTracker;
import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.queryHandlers.query.BaseQuery;

import java.util.List;

public interface QueryHandler<T extends BaseQuery> {
     <U extends BookTrackerDto> List<U> execute(T query);
}
