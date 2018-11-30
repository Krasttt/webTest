package com.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;

@Entity
public class Test {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Integer amountQuestions;
    private String discription;
    public Duration duration;

    public Test() {
    }

    public Test(String name, String description, Integer amountQuestions, Duration duration) {
        this.name = name;
        this.amountQuestions = amountQuestions;
        this.discription = description;
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountQuestions() {
        return amountQuestions;
    }

    public void setAmountQuestions(Integer amountQuestions) {
        this.amountQuestions = amountQuestions;
    }
}
