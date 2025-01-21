package com.example.bookstorageservice.queryHandlers.dispatcher;

import com.example.bookstorageservice.entity.DTO.BaseDto;
import com.example.bookstorageservice.queryHandlers.handlers.QueryHandler;
import com.example.bookstorageservice.queryHandlers.query.BaseQuery;

import java.util.List;

public interface QueryDispatch {
    <T extends BaseQuery> void regQuery(Class<T> query, QueryHandler<T> handler );
    <U extends BaseDto> List<U> dispatch(BaseQuery baseQuery);
}
