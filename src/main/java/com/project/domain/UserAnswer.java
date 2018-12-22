package com.project.domain;

import javax.persistence.*;

@Entity

public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private boolean correct;
    @ManyToOne
    private Question question;
    @ManyToOne
    private UserAccount userAccount;
    @ManyToOne
    private Result result;

    public UserAnswer() {
    }

    public UserAnswer(String text, boolean correct, Question question, Result result,UserAccount user) {
        this.text = text;
        this.correct = correct;
        this.question = question;
        this.result = result;
        this.userAccount=user;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
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
