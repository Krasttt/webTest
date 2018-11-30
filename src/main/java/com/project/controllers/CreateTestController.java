package com.project.controllers;

import com.project.domain.Test;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;

@Controller
public class CreateTestController {
    final TestRepository testRepository;
    final QuestionRepository questionRepository;

    public CreateTestController(TestRepository testRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }


    @PostMapping("/createtest")
    public String showTests(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Integer amtQuestions,
            @RequestParam Integer duration,
            Model model
    ) {
        Test test = new Test(name, description, amtQuestions, Duration.ofMinutes(duration));
        testRepository.save(test);
        return "redirect:/tests";
    }

    @RequestMapping("/addquestion")
    public String addQuestion() {

        return "addQuestion";
    }


    @GetMapping("/createtest")
    public String createTest() {
        return "createtest";
    }
}
