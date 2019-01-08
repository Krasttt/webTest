package com.project.controllers;

import com.project.Sevices.TestService;
import com.project.domain.Result;
import com.project.domain.Test;
import com.project.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestService testService;

    @GetMapping("/tests")
    public String showTests(
            @RequestParam(defaultValue = "") String filter,
            Model model) {
        List<Test> tests = new ArrayList<>();
        if (filter != "" && !filter.isEmpty()) {
            tests = testRepository.findByNameContaining(filter);
        } else tests = testRepository.findAllBy();
        model.addAttribute("tests", tests);

        List<Result> activeResults = testService.getAllActiveResult(true);
        for (Result result: activeResults) {
            System.out.println(new Date().getTime()-result.getStartTest().getTime());
            System.out.println(result.getTest().getDuration());
            System.out.println(result.getTest().getDuration().toMillis());
            if (new Date().getTime()-result.getStartTest().getTime()>=result.getTest().getDuration().toMillis()){
                testService.setResult(result.getTest().getId(),result.getId());
            }
        }
        return "tests";
    }


    @RequestMapping("/")
    public String index() {
        return "index";
    }


}
