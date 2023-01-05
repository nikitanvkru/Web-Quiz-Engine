package com.example.restfulwebquiz.service;


import com.example.restfulwebquiz.controller.QuizRequestBody;
import com.example.restfulwebquiz.controller.QuizResponse;
import com.example.restfulwebquiz.controller.QuizSolutionResponse;
import com.example.restfulwebquiz.persistence.Quiz;
import com.example.restfulwebquiz.persistence.QuizOption;
import com.example.restfulwebquiz.persistence.QuizOptionRepository;
import com.example.restfulwebquiz.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Web service class, that bundles all the endpoint functionality (as of now) for the
 * WebQuizController REST-controller.
 */
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizOptionRepository quizOptionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizOptionRepository quizOptionRepository) {
        this.quizRepository = quizRepository;
        this.quizOptionRepository = quizOptionRepository;
    }

    /**
     * service corresponding to GET endpoints "api/quiz" and "api/quizzes/{id}"
     * @param id the queried quiz id
     * @return the quiz as retrieved from the QuizGenerator component or a 404 NOT FOUND
     */
    public ResponseEntity<QuizResponse> getQuizById(int id) {
        Quiz quiz = findQuizByIdOrThrow(id);
        return ResponseEntity.ok(new QuizResponse(quiz));
    }

    /**
     * service corresponding to GET endpoints "api/quizzes"
     * @return all quizzes from the QuizGenerator component
     */
    public Iterable<QuizResponse> getQuizzes() {
        Iterable<Quiz> quizIterator= quizRepository.findAll();
        List<QuizResponse> quizzes = new ArrayList<>();
        quizIterator.forEach(quiz -> quizzes.add(new QuizResponse(quiz)));
        return quizzes;
    }

    /**
     * service corresponding to POST endpoints "api/quizzes".
     * calls the QuizGenerator with the QuizRequestBody to create this quiz
     * @param quizRequestBody the QuizRequestBody DTO as received by POST
     * @return the created quiz information - also displaying the id-key to client
     *
     */
    public QuizResponse createQuiz(QuizRequestBody quizRequestBody) {
        System.out.println("***" + quizRequestBody);
        Quiz quiz = quizRepository.save(new Quiz(quizRequestBody.getTitle(), quizRequestBody.getText()));
        quiz.setOptions(createAndSaveOptionsList(quizRequestBody, quiz));
        return new QuizResponse(quiz);
    }

    private List<QuizOption> createAndSaveOptionsList(QuizRequestBody quizRequestBody, Quiz parentQuiz) {
        List<QuizOption> options = new ArrayList<>();
        Set<Integer> correctOptions = new HashSet<>();
        for (int i = 0; quizRequestBody.getAnswer() != null && i < quizRequestBody.getAnswer().length; i++) {
            correctOptions.add(quizRequestBody.getAnswer()[i]);
        }
        int count = 0;
        for (String option : quizRequestBody.getOptions()) {
            options.add(quizOptionRepository.save(
                    new QuizOption(parentQuiz, option, count, correctOptions.contains(count))));
            count++;
        }
        return options;
    }

    /**
     * service corresponding to POST endpoints "api/quizzes/{id}/solve"
     * @param id the queried quiz id
     * @param answer the answer option, that the user chose
     * @return a feedback message on correctness of answer option or a 404 NOT FOUND
     */
    public ResponseEntity<QuizSolutionResponse> returnSolveResponse(int id, int[] answer) {
        Quiz quiz = findQuizByIdOrThrow(id);
        Arrays.sort(answer);
        if (Arrays.equals(answer, quiz.getCorrectOptions())) {
            return ResponseEntity.ok(QuizSolutionResponse.correct());
        }
        return ResponseEntity.ok(QuizSolutionResponse.incorrect());
    }

    private Quiz findQuizByIdOrThrow(long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException();
        }
        return quiz.get();
    }
}
