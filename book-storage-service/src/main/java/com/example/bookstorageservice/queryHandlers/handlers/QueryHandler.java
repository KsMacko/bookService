package com.example.bookstorageservice.queryHandlers.handlers;

import com.example.bookstorageservice.entity.DTO.BaseDto;
import com.example.bookstorageservice.queryHandlers.query.BaseQuery;

import java.util.List;

public interface QueryHandler <T extends BaseQuery>{
    <U  extends BaseDto> List<U> execute(T query);
}
