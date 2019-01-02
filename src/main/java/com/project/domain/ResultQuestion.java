package com.project.domain;

import javax.persistence.*;

@Entity
public class ResultQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    private Result result;
    private boolean correctness;

    public ResultQuestion(String text, Type type, Result result, boolean correctness) {
        this.text = text;
        this.type = type;
        this.result = result;
        this.correctness = correctness;
    }

    public ResultQuestion() {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }
}
