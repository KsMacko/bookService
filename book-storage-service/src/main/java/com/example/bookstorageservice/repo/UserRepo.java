package com.example.bookstorageservice.repo;

import com.example.bookstorageservice.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> getUserByLogin(String login);
}
