package com.project.controllers;

import com.project.domain.Test;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    final TestRepository testRepository;
    final QuestionRepository questionRepository;

    public MainController(TestRepository testRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/tests")
    public String showTests(
            @RequestParam(defaultValue = "") String filter,
            Model model) {
        List<Test> tests = new ArrayList<>();
        if (filter != "" && !filter.isEmpty()) {
            tests = testRepository.findByNameContaining(filter);
        } else tests = testRepository.findAllBy();
        model.addAttribute("tests", tests);
        model.addAttribute("name", "User");
        return "tests";
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }


}
