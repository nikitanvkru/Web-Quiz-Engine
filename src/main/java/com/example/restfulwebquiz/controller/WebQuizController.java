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


    /**
     * API name: Get all quizzes
     * HTTP method: Get
     *
     * @return all existing quizzes
     * @path api/quizzes
     */
    @GetMapping("api/quizzes")
    public Iterable<QuizResponse> getQuizzes() {
        return quizService.getQuizzes();
    }


    /**
     * API name: Get quiz by id
     * HTTP method: Get
     *
     * @return quiz obj from given id
     * @path api/quizzes/{id}
     */
    @GetMapping("api/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable("id") int id) {
        return quizService.getQuizById(id);
    }

    /**
     * API name: Create custom quiz
     * HTTP method: Post
     *
     * @return created quiz obj with some properties
     * @path api/quizzes
     */
    @PostMapping("api/quizzes")
    public QuizResponse createQuiz(@Valid @RequestBody QuizRequestBody quizRequestBody) {
        return quizService.createQuiz(quizRequestBody);
    }

    /**
     * API name: Solve quiz by its id
     * HTTP method: Post
     *
     * @return wrong or right message if quiz exists and 404 if not
     * @path api/quizzes/{id}/solve
     */
    @PostMapping("api/quizzes/{id}/solve")
    public ResponseEntity<QuizSolutionResponse> answerQuiz(@PathVariable("id") int id,
                                                           @RequestBody Map.Entry<String, int[]> answerEntry) {
        return quizService.returnSolveResponse(id, answerEntry.getValue());
    }
}