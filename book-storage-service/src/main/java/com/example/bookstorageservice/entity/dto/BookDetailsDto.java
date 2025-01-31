package com.example.bookstorageservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsDto {
    private BookDto bookDto;
    private List<BookTracker> bookTracker;
}
