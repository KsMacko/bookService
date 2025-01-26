package com.example.bookstorageservice.queryHandlers.query;

public class FindBookTrackerById extends BaseQuery{
    private  Long id;

    public FindBookTrackerById(Long id){
        this.id= id;
    }
    public Long getId() {
        return id;
    }
}
