package com.example.booktrackerservice.entities;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrackerHandler {
    private final ModelMapper modelMapper;

    public TrackerHandler() {
        this.modelMapper = new ModelMapper();
    }
    public BookTrackerDto handleToDto(BookTracker bookTracker){
        return modelMapper.map(bookTracker, BookTrackerDto.class);
    }
    public BookTracker handleToEntity(BookTrackerDto bookTrackerDto){
        return modelMapper.map(bookTrackerDto, BookTracker.class);
    }
}
