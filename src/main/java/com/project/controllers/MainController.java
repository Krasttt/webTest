package com.project.controllers;

import com.project.Sevices.impl.TestServiceImpl;
import com.project.domain.Result;
import com.project.domain.Test;
import com.project.repositories.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private final TestRepository testRepository;
    private final TestServiceImpl testServiceImpl;

    public MainController(TestRepository testRepository, TestServiceImpl testServiceImpl) {
        this.testRepository = testRepository;
        this.testServiceImpl = testServiceImpl;
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

        List<Result> activeResults = testServiceImpl.getAllActiveResult(true);
        for (Result result: activeResults) {
            if (new Date().getTime()-result.getStartTest().getTime()>=result.getTest().getDuration().toMillis()){
                testServiceImpl.setResult(result.getTest().getId(),result.getId());
            }
        }
        return "test/tests";
    }


    @RequestMapping("/")
    public String index() {
        return "redirect:/tests";
    }


}
