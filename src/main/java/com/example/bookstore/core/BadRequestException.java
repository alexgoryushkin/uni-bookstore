package com.example.bookstore.core;

/**
 * Исключение, показывающее, что данные, пришедшие от пользователя некорректны
 */
public class BadRequestException extends UserResponseRuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BadRequestException build(String userResponse) {
        BadRequestException ex = new BadRequestException(userResponse);
        ex.setUserMessage(userResponse);
        return ex;
    }

}
