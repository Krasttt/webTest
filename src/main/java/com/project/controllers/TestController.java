package com.project.controllers;

import com.project.Sevices.TestService;
import com.project.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String showTest(@RequestParam(defaultValue = "1") Integer id, Model model,
                           @AuthenticationPrincipal UserAccount user) {
        List<Question> questions = testService.getQuestions(id);
        List<Answer> answers = testService.getAnswers(questions);

        model.addAttribute("result", testService.createResult(id, user));
        model.addAttribute("allAnswers", answers);
        model.addAttribute("questions", questions);
        model.addAttribute("test", testService.getTest(id));
        return "test";
    }

    @PostMapping("/test/addAnswer/{id}/{result_id}")
    @ResponseBody
    public void confirmAnswer(@PathVariable Integer id, @PathVariable("result_id") Integer resultId,
                              @RequestBody List<Answer> answers, @AuthenticationPrincipal UserAccount user
    ) {
        testService.confirmAnswer(id, resultId, answers, user);
    }
    @RequestMapping("/info")
    public String showTestInfo(@RequestParam Integer id, Model model) {
        Test test = testService.getTest(id);
        model.addAttribute("duration", test.getDuration().toMinutes());
        model.addAttribute("test", test);
        return "info";
    }
    @RequestMapping("/testResult/{test_id}/{result_id}")
    public String showTestResult(@PathVariable("test_id") Integer testId, @PathVariable("result_id") Integer resultId,
                                 Model model) {
        Map<String, Integer> map = testService.setResult(testId, resultId);

        model.addAttribute("countRightAnswers", map.get("countRightAnswers"));
        model.addAttribute("amountQuestions", map.get("amountQuestions"));
        model.addAttribute("test", testService.getTest(testId));
        model.addAttribute("result", testService.getResult(resultId));

        return "testResult";
    }
    @RequestMapping("/testResult/{result_id}")
    public String showUserResult(@PathVariable("result_id") Integer resultId,Model model){
        Result result = testService.getResult(resultId);
        List<Question> questions = testService.getQuestions(result.getTest().getId());
        model.addAttribute("questions",questions);
        model.addAttribute("result",result);
        return "userResult";
    }
}
