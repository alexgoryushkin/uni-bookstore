package com.example.bookstore.core;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.bookstore.model.User;

public class AuthUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private final User user;

    public AuthUser(User user) {
        super(user.getLogin(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.toUpperCase())).collect(Collectors.toList());
    }
    
    public long getId() {
        return user.getId();
    }

}
