package com.example.restfulwebquiz.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/** DTO object representing a single answer option of a quiz.
 * The solution is not displayed to clients who attempt to solve this quiz,* therefore it is marked @JsonIgnore.
 * Instances of quizzes are only created by the QuizGenerator.
 */
@Entity
public class QuizOption {

    @JsonIgnore
    @Id
    @Column(name="answer_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long answerId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    @NotEmpty
    private String option;

    @JsonIgnore
    private int optionId;

    @JsonIgnore
    private boolean correct = false;

    public QuizOption() {
    }

    public QuizOption(Quiz quiz, String option, int optionId, boolean isCorrect) {
        this.quiz = quiz;
        this.option = option;
        this.optionId = optionId;
        this.correct = isCorrect;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
