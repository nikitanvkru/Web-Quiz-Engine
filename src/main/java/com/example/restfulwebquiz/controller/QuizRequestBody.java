package com.example.restfulwebquiz.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

/**
 * DTO object representing a quiz creation request body.
 * Different from Quiz objects, at this stage (before creation) no id has been assigned
 * and also different from GET requests the solution to the quiz has to be provided by client.
 */
public class QuizRequestBody {

    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    @Size(min=2)
    private String[] options;
    private int[] answer;

    public QuizRequestBody() {
    }

    public QuizRequestBody(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
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

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public String toString() {
        return "title: "+title+" text: "+text+" options:"+ Arrays.toString(options)
                +" answer: "+ Arrays.toString(answer);
    }
}
