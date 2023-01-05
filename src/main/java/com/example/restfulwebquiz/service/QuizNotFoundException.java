package com.example.restfulwebquiz.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException() {
        super("Invalid Quiz id given!");
    }
}
