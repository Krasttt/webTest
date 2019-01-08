package com.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String text;
    @NotNull
    private boolean correct;
    @NotNull
    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Answer(String text, boolean correct, Question question) {
        this.text = text;
        this.correct = correct;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
}
