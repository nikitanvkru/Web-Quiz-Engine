package com.example.restfulwebquiz.controller;

public class QuizSolutionResponse {

    public static QuizSolutionResponse correct() {
        return new QuizSolutionResponse(true, "Congratulations, you're right!");
    }

    public static QuizSolutionResponse incorrect() {
        return new QuizSolutionResponse(false, "Wrong answer! Please, try again.");
    }

    public QuizSolutionResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    private boolean success;
    private String feedback;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
