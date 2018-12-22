package com.project.domain;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    private Test test;

    public Question() {
    }

    public Question(String text, Type type, Test test) {
        this.text = text;
        this.type = type;
        this.test = test;
    }

    public Test getTest() {
        return test;
    }
    public void setTest(Test test) {
        this.test = test;
    }

    public Type getType() {
        return type;
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


}
