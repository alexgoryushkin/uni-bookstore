package com.example.bookstore.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.dto.NewUserDto;
import com.example.bookstore.model.User;

/**
 * Сервис по работе с пользователями
 */
public interface UserService extends UserDetailsService {

    /**
     * Создает пользователя
     * 
     * @param userData данные пользователя
     * @return данные созданного пользователя
     * @throws BadRequestException если данные пользователя некорректны
     */
    public User createUser(NewUserDto userData) throws BadRequestException;

}
