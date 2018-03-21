package com.example.bookstore.services.impl;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.bookstore.entities.AuthorEntity;
import com.example.bookstore.entities.BookEntity;
import com.example.bookstore.entities.OrderEntity;
import com.example.bookstore.entities.OrderEntryEntity;
import com.example.bookstore.entities.UserEntity;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.Order.OrderItem;
import com.example.bookstore.model.User;

import lombok.experimental.UtilityClass;

/**
 * Класс конвертор энтити обхектов в модельные
 */
@UtilityClass
class EntityModelMapper {

    public static Author toAuthor(AuthorEntity entity) {
        return Author.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static Book toBook(BookEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .isbn(entity.getISBN())
                .price(entity.getPrice())
                .year(entity.getYear())
                .authors(entity.getAuthors().stream().map(EntityModelMapper::toAuthor).collect(Collectors.toList()))
                .build();
    }

    public static User toUser(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .roles(new ArrayList<>(entity.getRoles()))
                .build();
    }

    public static Order toOrder(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .date(entity.getCreated())
                .books(entity.getBooks().stream().map(EntityModelMapper::toOrderItem).collect(Collectors.toList()))
                .build();
    }

    private static OrderItem toOrderItem(OrderEntryEntity entity) {
        return new OrderItem(toBook(entity.getBook()), entity.getPrice(), entity.getAmount());
    }

}
