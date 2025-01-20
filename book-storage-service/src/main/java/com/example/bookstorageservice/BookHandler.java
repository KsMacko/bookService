package com.example.bookstorageservice;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookHandler {
    private final ModelMapper modelMapper;

    public BookHandler(){
        this.modelMapper = new ModelMapper();
    }
    public BookDto handleToDto(BookEntity bookEntity){
        return modelMapper.map(bookEntity, BookDto.class);
    }
    public BookEntity handleToEntity(BookDto bookDto){
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
