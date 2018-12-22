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
    private String name;
    private Duration duration;
    private Integer grade;

    @ManyToOne
    private Test test;

    @ManyToOne
    private UserAccount user;

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Result(Date startTest, String name, Test test,UserAccount user) {
        this.startTest = startTest;
        this.name = name;
        this.test = test;
        this.user = user;
    }

    public Result() {
    }

    public void setStartTest(Date startTest) {
        this.startTest = startTest;
    }

    public Date getStartTest() {
        return startTest;
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

    public void setGrade(double percent) {
        if (percent <= 0.3) {
            this.grade = 2;
        } else if (percent <= 0.6) {
            this.grade = 3;
        } else if (percent <= 0.8) {
            this.grade = 4;
        } else if (percent <= 1) {
            this.grade = 5;
        }
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
