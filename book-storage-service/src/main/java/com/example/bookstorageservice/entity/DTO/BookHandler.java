package com.example.bookstorageservice.entity.DTO;

import com.example.bookstorageservice.entity.BookEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class BookHandler {
    private final ModelMapper modelMapper;

    public BookHandler(){
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }
    public BookDto handleToDto(BookEntity bookEntity){
        return modelMapper.map(bookEntity, BookDto.class);
    }
    public BookEntity handleToEntity(BookDto bookDto){
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
