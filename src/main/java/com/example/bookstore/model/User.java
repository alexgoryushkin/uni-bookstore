package com.example.bookstore.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Пользователь
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = { "id", "login" })
public class User {

    /**
     * Идентификатор пользователя
     */
    private long id;

    /**
     * Логин пользователя
     */
    private String login;

    /**
     * Имя пользователя
     */
    private String name;
    /**
     * Хэш пароля
     */
    private String password;

    /**
     * Почтовый ящик пользователя
     */
    private String email;

    /**
     * Роли пользователя
     */
    private List<String> roles;
}
