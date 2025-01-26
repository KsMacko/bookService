package com.example.booktrackerservice.queryHandlers.query;

public class GetTrackerById extends BaseQuery{
    Long id;

    public GetTrackerById(Long id){
        this.id=id;
    }
    public Long getId() {
        return id;
    }
}
