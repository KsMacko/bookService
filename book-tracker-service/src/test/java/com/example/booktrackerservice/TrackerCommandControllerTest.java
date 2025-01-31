package com.example.booktrackerservice;

import com.example.booktrackerservice.controller.TrackerCommandController;
import com.example.booktrackerservice.entities.dto.BookTrackerDto;
import com.example.booktrackerservice.service.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrackerCommandControllerTest {
    @Mock
    private CommandService commandService;

    @InjectMocks
    private TrackerCommandController trackerCommandController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(trackerCommandController).build();
    }

    @Test
    public void testCreateBookTracker() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/create_tracker/{bookId}", bookId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(commandService, times(1)).createBookTracker(bookId);
    }

    @Test
    public void testDeleteBookTracker() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete_tracker/{bookId}", bookId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(commandService, times(1)).deleteTrackerByBookId(bookId);
    }

    @Test
    public void testUpdateTracker() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/update_tracker")
                        .contentType("application/json")
                        .content("{ \"id\": 1, \"bookId\": 1, \"bookStatus\": \"AVAILABLE\", \"borrowDate\": \"2023-01-01\", \"returnDate\": \"2023-01-02\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(commandService, times(1)).changeBookStatus(any(BookTrackerDto.class));
    }
}

