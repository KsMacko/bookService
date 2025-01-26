package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.SaveBookToDatabase;
import com.example.bookstorageservice.entity.BookEntity;
import com.example.bookstorageservice.entity.DTO.BookHandler;
import com.example.bookstorageservice.feignClients.BookTrackerServiceFeignClient;
import com.example.bookstorageservice.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveBookCommandHandler implements CommandHandler<SaveBookToDatabase>{
    private final BookRepo bookRepo;
    private final BookHandler bookHandler;
    private final BookTrackerServiceFeignClient bookTrackerServiceFeignClient;

    @Override
    public void execute(SaveBookToDatabase query) {
        Optional<BookEntity> bookEntity;
        if (query.getBookDto().getId()!=null) {
            bookEntity = bookRepo.findById(query.getBookDto().getId());
            bookEntity.ifPresent(entity -> {
                entity.setAuthor(query.getBookDto().getAuthor());
                entity.setDescription(query.getBookDto().getDescription());
                entity.setName(query.getBookDto().getName());
                entity.setIsbn(query.getBookDto().getIsbn());
                entity.setGenre(query.getBookDto().getGenre());
                bookRepo.save(entity);

            });
        }else {
            BookEntity entity = bookHandler.handleToEntity(query.getBookDto());
            bookRepo.save(entity);
            if (query.getBookDto().getId() == null)
                bookTrackerServiceFeignClient.createBookTracker(entity.getId());
        }
    }
}
