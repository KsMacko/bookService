package com.example.bookstorageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@Controller
public class BookStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStorageServiceApplication.class, args);
    }
    @GetMapping("/")
    public String getMainPage(){
        return "actions";
    }
}
