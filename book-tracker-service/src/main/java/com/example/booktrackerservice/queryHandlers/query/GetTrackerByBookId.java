package com.example.booktrackerservice.queryHandlers.query;

public class GetTrackerByBookId extends BaseQuery{
    private Long bookId;

    public GetTrackerByBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}
