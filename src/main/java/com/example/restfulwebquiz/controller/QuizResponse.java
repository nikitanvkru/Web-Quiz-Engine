package com.example.restfulwebquiz.controller;

import com.example.restfulwebquiz.persistence.Quiz;
import com.example.restfulwebquiz.persistence.QuizOption;


public class QuizResponse {

    private long id;
    private String title;
    private String text;
    private String[] options;

    public QuizResponse() {
    }

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getQuizId();
        this.title = quiz.getTitle();
        this.text = quiz.getText();
        this.options = quiz.getOptions().size() == 0 ? new String[] {}
                : quiz.getOptions().stream().map(QuizOption::getOption).toArray(String[]::new);
    }

    public long getId() {
        return id;
    }

    public void setId(long quizId) {
        this.id = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
