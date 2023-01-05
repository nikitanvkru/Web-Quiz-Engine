package com.example.restfulwebquiz.persistence;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * DTO object representing a quiz.
 * The solution is not displayed to clients who attempt to solve this quiz,
 * therefore it is marked @JsonIgnore.
 * Instances of quizzes are only created by the QuizGenerator.
 */
@Entity
public class Quiz {

    @Id
    @Column(name="quiz_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long quizId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @OneToMany(mappedBy="quiz")
    @Column(name="answer")
    @Size(min=2)
    private List<QuizOption> options;

    public Quiz() {
    }

    public Quiz(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
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

    public List<QuizOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuizOption> options) {
        this.options = options;
    }

    public int[] getCorrectOptions() {
        return options.stream().filter(QuizOption::isCorrect)
                .mapToInt(QuizOption::getOptionId).toArray();
    }
}
