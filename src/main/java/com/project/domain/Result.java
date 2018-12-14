package com.project.domain;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date startTest;

    public Result(Date startTest, String name, Test test) {
        this.startTest = startTest;
        this.name = name;
        this.test = test;
    }

    public Date getStartTest() {
        return startTest;
    }

    public void setStartTest(Date startTest) {
        this.startTest = startTest;
    }

    private String name;
    private Duration duration;
    private Integer grade;

    @ManyToOne
    private Test test;

    public Result() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
