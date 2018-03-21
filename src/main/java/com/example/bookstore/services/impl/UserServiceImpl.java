package com.example.bookstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookstore.core.AuthUser;
import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.dto.NewUserDto;
import com.example.bookstore.entities.UserEntity;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UsersRepository;
import com.example.bookstore.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User createUser(NewUserDto userData) throws BadRequestException {
        UserEntity user = new UserEntity();
        user.setLogin(userData.getLogin());
        user.setName(userData.getName());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setEmail(userData.getEmail());
        return EntityModelMapper.toUser(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByLogin(username);
        if (entity == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с логином %s не найден", username));
        }
        User user = EntityModelMapper.toUser(entity);
        return new AuthUser(user);
    }

}
