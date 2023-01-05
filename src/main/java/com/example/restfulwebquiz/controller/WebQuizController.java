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
     * GET endpoint "api/quizzes" - returning out all available web quizzes
     * @return an array of all quiz objects created in this session starting with the Java quiz
     */
    @GetMapping("api/quizzes")
    public Iterable<QuizResponse> getQuizzes() {
        return quizService.getQuizzes();
    }

    /**
     * GET endpoint "api/quizzes/{id}" - with path variable {id}
     * e.g.: api/quiz/1 that returns the queried quiz if available or a 404-HTTP response
     * @param id the id of a quiz as path variable
     * @return the queried quiz if available or a 404-HTTP response
     */
    @GetMapping("api/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable("id") int id) {
        return quizService.getQuizById(id);
    }

    /**
     * POST endpoint "api/quizzes" - receives data to create a new quiz in the ResponseBody and
     * displays it with the id information from creation
     * @return the queried quiz if available or a 404-HTTP response
     */
    @PostMapping("api/quizzes")
    public QuizResponse createQuiz(@Valid @RequestBody QuizRequestBody quizRequestBody) {
        return quizService.createQuiz(quizRequestBody);
    }

    /**
     * POST endpoint "api/quizzes/{id}/solve" - receiving an Integer answer to the quiz
     * as request parameter - e.g.: api/quiz?answer=0, that corresponds to the solution option
     * the client chooses (starting with 0).
     * @param id the id of a quiz as path variable
     * @param answerEntry the id of a quiz as path variable
     * @return a boolean - string answer object QuizSolutionResponse
     */
    @PostMapping("api/quizzes/{id}/solve")
    public ResponseEntity<QuizSolutionResponse> answerQuiz(@PathVariable("id") int id,
                                                           @RequestBody Map.Entry<String, int[]> answerEntry) {
        return quizService.returnSolveResponse(id, answerEntry.getValue());
    }
}