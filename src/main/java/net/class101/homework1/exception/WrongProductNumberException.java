package net.class101.homework1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongProductNumberException extends RuntimeException {
    private String message;

    public WrongProductNumberException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}