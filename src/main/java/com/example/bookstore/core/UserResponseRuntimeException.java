package com.example.bookstore.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Исключение, содержащая сообщение, отправляемое пользователю в ответе
 */
public abstract class UserResponseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Сообщение, которое будет отправлено клиенту
     */
    @Getter
    @Setter
    private String userMessage;

    public UserResponseRuntimeException() {
    }

    public UserResponseRuntimeException(String message) {
        super(message);
    }

    public UserResponseRuntimeException(Throwable cause) {
        super(cause);
    }

    public UserResponseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
