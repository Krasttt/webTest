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
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToOne
    private Test test;
    @ManyToOne
    private UserAccount user;

    public Result() {
    }

    public Result(Date startTest, String name, Test test,UserAccount user,Integer grade,boolean active) {
        this.startTest = startTest;
        this.name = name;
        this.test = test;
        this.user = user;
        this.grade=grade;
        this.active=active;
    }

    public UserAccount getUser() {
        return user;
    }
    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Date getStartTest() {
        return startTest;
    }
    public void setStartTest(Date startTest) {
        this.startTest = startTest;
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
