package com.project.controllers;

import com.project.domain.Question;
import com.project.domain.Test;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    final TestRepository testRepository;
    final QuestionRepository questionRepository;

    public TestController(TestRepository testRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }


    @RequestMapping("/test")
    public String test(
            @RequestParam(defaultValue = "1") Integer id,
            Model model) {

        Iterable<Question> questions = questionRepository.findByTestId(id);
        model.addAttribute("questions", questions);
        return "test";
    }

    @RequestMapping("/info")
    public String info(@RequestParam String id, Model model) {
        Integer i = Integer.parseInt(id);
        Test test = testRepository.findById(i);
        if (test.duration != null)
            model.addAttribute("duration", test.duration.toMinutes());
        else model.addAttribute("duration", 0);

        model.addAttribute("test", test);
        return "info";
    }
}
