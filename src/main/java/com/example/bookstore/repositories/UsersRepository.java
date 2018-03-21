package com.example.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entities.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Найти пользователя по логину
     * 
     * @param login логин пользователя
     * @return пользователя
     */
    public UserEntity findByLogin(String login);
}
