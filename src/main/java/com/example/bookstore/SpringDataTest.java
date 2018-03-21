package com.example.bookstore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.bookstore.repositories.BooksRepository;

@SpringBootApplication
public class SpringDataTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringDataTest.class).web(false).run(args);
        BooksRepository booksRepo = context.getBean(BooksRepository.class);
        booksRepo.findAll();
    }
}
