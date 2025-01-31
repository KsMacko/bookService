package com.example.booktrackerservice.entities;

import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;


@Component
public class TrackerHandler {
    private final ModelMapper modelMapper;
    public TrackerHandler() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }
    public BookTrackerDto handleToDto(BookTracker bookTracker){
        return modelMapper.map(bookTracker, BookTrackerDto.class);
    }
    public BookTracker handleToEntity(BookTrackerDto bookTrackerDto){
        return modelMapper.map(bookTrackerDto, BookTracker.class);
    }
}
