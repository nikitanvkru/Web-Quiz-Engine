package com.example.restfulwebquiz.controller;


import com.example.restfulwebquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import javax.validation.Valid;

@RestController
public class WebQuizController {

    private final QuizService quizService;

    @Autowired
    public WebQuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping("api/quizzes")
    public Iterable<QuizResponse> getQuizzes() {
        return quizService.getQuizzes();
    }


    @GetMapping("api/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable("id") int id) {
        return quizService.getQuizById(id);
    }


    @PostMapping("api/quizzes")
    public QuizResponse createQuiz(@Valid @RequestBody QuizRequestBody quizRequestBody) {
        return quizService.createQuiz(quizRequestBody);
    }


    @PostMapping("api/quizzes/{id}/solve")
    public ResponseEntity<QuizSolutionResponse> answerQuiz(@PathVariable("id") int id,
                                                           @RequestBody Map.Entry<String, int[]> answerEntry) {
        return quizService.returnSolveResponse(id, answerEntry.getValue());
    }
}