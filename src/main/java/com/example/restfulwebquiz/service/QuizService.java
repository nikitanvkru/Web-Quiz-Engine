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
 * QuizService class, holds the main logic of the WebQuizController class
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
     * @param id is the quiz id we want to get
     * @return quiz obj if quiz with passed id is present, else return 404 status code
     */
    public ResponseEntity<QuizResponse> getQuizById(int id) {
        Quiz quiz = findQuizByIdOrThrow(id);
        return ResponseEntity.ok(new QuizResponse(quiz));
    }

    /**
     * service corresponding to GET endpoints "api/quizzes"
     * @return all existing quizzes
     */
    public Iterable<QuizResponse> getQuizzes() {
        Iterable<Quiz> quizIterator= quizRepository.findAll();
        List<QuizResponse> quizzes = new ArrayList<>();
        quizIterator.forEach(quiz -> quizzes.add(new QuizResponse(quiz)));
        return quizzes;
    }

    /**
     * service corresponding to POST endpoints "api/quizzes".
     * @param quizRequestBody the QuizRequestBody pojo posted by user
     * @return some fields from posted quiz
     *
     */
    public QuizResponse createQuiz(QuizRequestBody quizRequestBody) {
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
     * @param id the quiz we want to solve by id
     * @param answer the user answer
     * @return wrong or right message if quiz exists and 404 if not
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
