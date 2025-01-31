package com.example.booktrackerservice;

import com.example.booktrackerservice.controller.TrackerQueryController;
import com.example.booktrackerservice.service.QueryService;
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

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrackerQueryControllerTest {
    @Mock
    private QueryService queryService;

    @InjectMocks
    private TrackerQueryController trackerQueryController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(trackerQueryController).build();
    }

    @Test
    public void testGetFreeBooks() throws Exception {
        when(queryService.showFreeBooks()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/get_free_book_trackers")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        verify(queryService, times(1)).showFreeBooks();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        when(queryService.showAllBookTrackers()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/get_all_book_trackers")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        verify(queryService, times(1)).showAllBookTrackers();
    }

    @Test
    public void testGetTrackerByBookId() throws Exception {
        Long bookId = 1L;
        when(queryService.getTrackerByBookId(bookId)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/get_tracker_byBook/{book_id}", bookId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        verify(queryService, times(1)).getTrackerByBookId(bookId);
    }
}
