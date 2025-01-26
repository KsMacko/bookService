package com.example.bookstorageservice.controller;

import com.example.bookstorageservice.entity.DTO.BookDetailsDto;
import com.example.bookstorageservice.entity.DTO.BookDto;
import com.example.bookstorageservice.entity.DTO.BookTracker;
import com.example.bookstorageservice.entity.DTO.Status;
import com.example.bookstorageservice.queryHandlers.dispatcher.BookQueryDispatcher;
import com.example.bookstorageservice.queryHandlers.query.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookQueryController {
    private final BookQueryDispatcher bookQueryDispatcher;

    @GetMapping("/")
    @Operation
    public String getMainPage(Model model) {
        List<BookDetailsDto> books = bookQueryDispatcher.dispatch(new FindAllBooksQuery());
        model.addAttribute("books", books);
        return "actions";
    }

    @GetMapping("/open_add_form")
    @Operation
    public String addBook(Model model) {
        model.addAttribute("book", new BookDto());
        return "add_book";
    }

    @GetMapping("/open_update_form/{id}")
    @Operation
    public String updateBook(@PathVariable Long id, Model model){
        List<BookDetailsDto> bookDtos = bookQueryDispatcher.dispatch(new FindBookById(id));
        if (!bookDtos.isEmpty()) model.addAttribute("book", bookDtos.get(0).getBookDto());
        return "update_book";
    }
    @GetMapping("/search_by_id")
    @Operation
    public String searchById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("books", bookQueryDispatcher.dispatch(new FindBookById(id)));
        return "actions";
    }
    @GetMapping("/search_by_isbn")
    @Operation
    public String searchByISBN(@RequestParam("isbn") String isbn, Model model) {
        model.addAttribute("books",bookQueryDispatcher.dispatch(new FindBookByISBN(isbn)));
        return "actions";
    }
    @GetMapping("/open_update_tracker/{id}")
    @Operation
    public String getChangeStatusPage(@PathVariable("id") Long id, Model model){
        List<BookTracker> trackers = bookQueryDispatcher.dispatch(new FindBookTrackerById(id));
        if(!trackers.isEmpty()){
            model.addAttribute("book_tracker", trackers.get(0));
            model.addAttribute("allowed_statuses", getAllowedStatuses(trackers.get(0).getBookStatus()));
        }
        return "changeStatus";
    }
    @GetMapping("/openHistory_byBook/{bookId}")
    @Operation
    public String openBookHistory(@PathVariable("bookId") Long bookId, Model model) {
        List<BookDetailsDto> bookDetails = bookQueryDispatcher.dispatch(new FindBookById(bookId));
        if(!bookDetails.isEmpty()){
            model.addAttribute("book", bookDetails.get(0).getBookDto());
            model.addAttribute("book_trackers", bookDetails.get(0).getBookTracker());
        }
        return "book_history";
    }
    @GetMapping("/show_free")
    @Operation
    public String showFreeBooks(Model model){
        List<BookDetailsDto> books = bookQueryDispatcher.dispatch(new GetFreeBooks());
        model.addAttribute("books", books);
        return "actions";
    }
    private List<Status> getAllowedStatuses(Status currentStatus) {
        List<Status> allowedStatuses = new ArrayList<>();
        switch (currentStatus) {
            case AVAILABLE -> {
                allowedStatuses.add(Status.BORROWED);
                break;
            }
            case BORROWED -> {
                allowedStatuses.add(Status.AVAILABLE);
                allowedStatuses.add(Status.OVERDUE);
                allowedStatuses.add(Status.RENEWED);
                break;
            }
            case OVERDUE -> {
                allowedStatuses.add(Status.AVAILABLE);
                allowedStatuses.add(Status.RENEWED);
                break;
            }
            case RENEWED -> {
                allowedStatuses.add(Status.AVAILABLE);
                allowedStatuses.add(Status.OVERDUE);
                break;
            }
        }
        return allowedStatuses;
    }
}
