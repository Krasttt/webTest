package com.project.controllers;

import com.project.Sevices.TestService;
import com.project.domain.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    private static final String RESULT_ATTRIBUTE_NAME="result";
    private static final String TEST_ATTRIBUTE_NAME="test";

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping("/test")
    public String showTest(@RequestParam(defaultValue = "1") Integer id, Model model,
                           @AuthenticationPrincipal UserAccount user) {
        List<Question> questions = testService.getQuestions(id);
        List<Answer> answers = testService.getAnswers(questions);

        Result result = testService.getActiveResult(id);
        if (result==null){
            model.addAttribute(RESULT_ATTRIBUTE_NAME, testService.createResult(id, user));

        }
        else {
            model.addAttribute(RESULT_ATTRIBUTE_NAME, result);
            List<ResultQuestion> resultQuestions = testService.getResultQuestions(result.getId());
            List<Question> deleteCandidates = new ArrayList<>();
            for (Question question:questions) {
                for (ResultQuestion resultQuestion: resultQuestions) {
                    if (question.equals(resultQuestion)){
                        deleteCandidates.add(question);
                    }
                }
            }
            for (Question deleteCandidate:deleteCandidates) {
               questions.remove(deleteCandidate);
            }
        }
        model.addAttribute("allAnswers", answers);
        model.addAttribute("questions", questions);
        model.addAttribute(TEST_ATTRIBUTE_NAME, testService.getTest(id));
        return "test/test";
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
        model.addAttribute(TEST_ATTRIBUTE_NAME, test);
        return "test/info";
    }
    @RequestMapping("/testResult/{test_id}/{result_id}")
    public String showTestResult(@PathVariable("test_id") Integer testId, @PathVariable("result_id") Integer resultId,
                                 Model model) {
        Map<String, Integer> map = testService.setResult(testId, resultId);

        model.addAttribute("countRightAnswers", map.get("countRightAnswers"));
        model.addAttribute("amountQuestions", map.get("amountQuestions"));
        model.addAttribute(TEST_ATTRIBUTE_NAME, testService.getTest(testId));
        model.addAttribute(RESULT_ATTRIBUTE_NAME, testService.getResult(resultId));

        return "test/testResult";
    }
    @RequestMapping("/testResult/{result_id}")
    public String showUserResult(@PathVariable("result_id") Integer resultId,Model model){
        Result result = testService.getResult(resultId);
        List<ResultQuestion> questions = testService.getResultQuestions(result.getId());
        model.addAttribute("questions",questions);
        model.addAttribute(RESULT_ATTRIBUTE_NAME,result);
        return "user/userResult";
    }
}
