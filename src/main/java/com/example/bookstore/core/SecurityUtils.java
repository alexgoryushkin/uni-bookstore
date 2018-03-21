package com.example.bookstore.core;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtils {

    /**
     * Получить идентификатор текущего пользователя
     * 
     * @return идентификатор текущего пользователя
     */
    public static long getCurrentUserId() {
        return ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
