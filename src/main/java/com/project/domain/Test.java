package com.project.domain;

import javax.persistence.*;
import java.time.Duration;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer amountQuestions;
    private String description;
    private Duration duration;
    @ManyToOne
    private UserAccount user;

    public Test() {
    }

    public Test(String name, String description, Integer amountQuestions, Duration duration, UserAccount user) {
        this.name = name;
        this.amountQuestions = amountQuestions;
        this.description = description;
        this.duration = duration;
        this.user = user;
    }

    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
