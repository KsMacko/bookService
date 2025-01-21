package com.example.bookstorageservice.queryHandlers.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
public class FindBookById extends BaseQuery{
    private Long id;

    public FindBookById(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
