package com.example.bookstorageservice;

import com.example.bookstorageservice.controller.BookQueryController;
import com.example.bookstorageservice.entity.dto.BookDetailsDto;
import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.service.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookQueryControllerTest {
    @Mock
    private QueryService queryService;

    @InjectMocks
    private BookQueryController bookQueryController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        BookDetailsDto bookDetails = new BookDetailsDto(
                new BookDto(1L, "example", "example", "example", "example", "example"),
                Collections.emptyList());
        when(queryService.findAllBooks()).thenReturn(Collections.singletonList(bookDetails));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/find_all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.name").value("example"));

        verify(queryService, times(1)).findAllBooks();
    }
    @Test
    public void testSearchByIsbn() throws Exception {
        String isbn = "example";
        BookDetailsDto bookDetails = new BookDetailsDto(
                new BookDto(1L, isbn, "example", "example", "example", "example"),
                Collections.emptyList());
        when(queryService.findBookByIsbn(isbn)).thenReturn(Collections.singletonList(bookDetails));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/search_by_isbn")
                        .param("isbn", isbn))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(isbn));

        verify(queryService, times(1)).findBookByIsbn(isbn);
    }
    @Test
    public void testSearchById() throws Exception {
        Long bookId = 1L;
        BookDetailsDto bookDetails = new BookDetailsDto(new BookDto(1L, "12345", "Book 1", "Genre", "Description", "Author"), Collections.emptyList());
        when(queryService.findBookById(bookId)).thenReturn(bookDetails);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/search_by_id")
                        .param("id", String.valueOf(bookId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookDto.name").value("Book 1"));

        verify(queryService, times(1)).findBookById(bookId);
    }

    @Test
    public void testShowFreeBooks() throws Exception {
        BookDetailsDto bookDetails = new BookDetailsDto(new BookDto(1L, "12345", "Free Book", "Genre", "Description", "Author"), Collections.emptyList());
        when(queryService.getFreeBooks()).thenReturn(Collections.singletonList(bookDetails));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/show_free"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.name").value("Free Book"));

        verify(queryService, times(1)).getFreeBooks();
    }
}
