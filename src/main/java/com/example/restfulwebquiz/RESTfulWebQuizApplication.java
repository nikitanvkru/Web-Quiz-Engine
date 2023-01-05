package com.example.restfulwebquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Web quiz engine project at the jetbrains academy stage 4
 * RESTful web service for creating and solving quizzes
 * Postgres as database for storing quizzes
 * @version 1.0
 * @autor Nikita Kruychkov
 */
@SpringBootApplication
public class RESTfulWebQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(RESTfulWebQuizApplication.class, args);
    }

}
