package com.example.bookstorageservice;

import org.modelmapper.ModelMapper;

public class BookHandler {
    private final ModelMapper modelMapper;

    public BookHandler(){
        this.modelMapper = new ModelMapper();
    }
    public BookDto handleToDto(BookEntity bookEntity){
        return modelMapper.map(bookEntity, BookDto.class);
    }
}
