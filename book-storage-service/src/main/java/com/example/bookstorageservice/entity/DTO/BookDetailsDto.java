package com.example.bookstorageservice.entity.DTO;

public class BookDetailsDto extends BaseDto{
    private BookDto bookDto;
    private BookTracker bookTracker;

    public BookDetailsDto(BookDto bookDto, BookTracker bookTracker) {
        this.bookDto = bookDto;
        this.bookTracker = bookTracker;
    }

    public BookTracker getBookTracker() {
        return bookTracker;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public void setBookTracker(BookTracker bookTracker) {
        this.bookTracker = bookTracker;
    }
}
