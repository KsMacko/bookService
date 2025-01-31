package com.example.bookstorageservice.entity;

import com.example.bookstorageservice.entity.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BookHandler {
    private final ModelMapper modelMapper;

    public BookHandler() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public BookDto handleToDto(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    public BookEntity handleToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
