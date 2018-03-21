package com.example.bookstore.core;

/**
 * Исключение, показывающее, что ресурс не найден
 */
public class NotFoundException extends UserResponseRuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static NotFoundException build(String userResponse) {
        NotFoundException ex = new NotFoundException(userResponse);
        ex.setUserMessage(userResponse);
        return ex;
    }

}
