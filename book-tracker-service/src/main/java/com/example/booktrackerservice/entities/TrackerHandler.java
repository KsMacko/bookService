package com.example.booktrackerservice.entities;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Component
public class TrackerHandler {
    private final ModelMapper modelMapper;

//    private static final Converter<Date, LocalDate> dateToLocalDate = mappingContext -> {
//        Date source = mappingContext.getSource();
//        return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    };
//
//    private static final Converter<LocalDate, Date> localDateToDate = mappingContext -> {
//        LocalDate source = mappingContext.getSource();
//        return Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
//    };
    public TrackerHandler() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
//        TypeMap<BookTracker, BookTrackerDto> entityToDto = modelMapper.createTypeMap(BookTracker.class, BookTrackerDto.class);
//        entityToDto.addMappings(mapping ->
//                mapping.using(dateToLocalDate)
//                        .map(BookTracker::getBorrowDate, BookTrackerDto::setBorrowDate));
//        entityToDto.addMappings(mapping ->
//                mapping.using(dateToLocalDate)
//                        .map(BookTracker::getReturnDate, BookTrackerDto::setReturnDate));
//        TypeMap<BookTrackerDto, BookTracker> DtoToEntity = modelMapper.createTypeMap(BookTrackerDto.class, BookTracker.class);
//        DtoToEntity.addMappings(mapping ->
//                mapping.using(localDateToDate)
//                        .map(BookTrackerDto::getBorrowDate, BookTracker::setBorrowDate));
//        DtoToEntity.addMappings(mapping ->
//                mapping.using(localDateToDate)
//                        .map(BookTrackerDto::getReturnDate, BookTracker::setReturnDate));
    }
    public BookTrackerDto handleToDto(BookTracker bookTracker){
        return modelMapper.map(bookTracker, BookTrackerDto.class);
    }
    public BookTracker handleToEntity(BookTrackerDto bookTrackerDto){
        return modelMapper.map(bookTrackerDto, BookTracker.class);
    }
}
