package com.project.domain;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    private String text;

    public Question(String text, Type type, Test test) {
        this.text = text;
        this.type = type;
        this.test = test;
    }

    public Type getType() {
        return type;
    }

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne()
    private Test test;

    @ManyToOne
    private Result result;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question() {
    }
}
