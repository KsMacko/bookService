package com.example.bookstorageservice;

import com.example.bookstorageservice.controller.BookCommandController;
import com.example.bookstorageservice.entity.Status;
import com.example.bookstorageservice.entity.dto.BookDto;
import com.example.bookstorageservice.entity.dto.BookTracker;
import com.example.bookstorageservice.service.CommandService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookCommandControllerTest {
    @Mock
    private CommandService commandService;

    @InjectMocks
    private BookCommandController bookCommandController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookCommandController).build();
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 1L;

        doNothing().when(commandService).deleteBook(bookId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete/{id}", bookId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(commandService, times(1)).deleteBook(bookId);
    }

    @Test
    public void testSaveBook() throws Exception {
        BookDto bookDto = new BookDto(1L, "1234567890", "Test Book", "Genre", "Description", "Author");

        doNothing().when(commandService).saveBook(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/book/save")
                        .contentType("application/json")
                        .content("{ \"id\": 1, \"isbn\": \"1234567890\", \"name\": \"Test Book\", \"genre\": \"Genre\", \"description\": \"Description\", \"author\": \"Author\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(commandService, times(1)).saveBook(bookDto);
    }

    @Test
    public void testUpdateTracker() throws Exception {
        BookTracker tracker = new BookTracker(1L, 1L, Status.AVAILABLE, null, null);

        doNothing().when(commandService).createNewTracker(tracker.getBookId());

        mockMvc.perform(MockMvcRequestBuilders.put("/book/tracker/update")
                        .contentType("application/json")
                        .content("{ \"id\": 1, \"bookId\": 1, \"bookStatus\": \"AVAILABLE\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(commandService, times(1)).createNewTracker(tracker.getBookId());
    }
}
