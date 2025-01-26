package com.example.bookstorageservice.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsDto extends BaseDto{
    private BookDto bookDto;
    private List<BookTracker> bookTracker;
}
