package com.api.emotibook.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
