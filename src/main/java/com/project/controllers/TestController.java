package com.project.controllers;

import com.project.domain.*;
import com.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class TestController {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @RequestMapping("/test")
    public String showTest(
            @RequestParam(defaultValue = "1") Integer id,
            Model model,
            @AuthenticationPrincipal UserAccount user) {
        List<Answer> allAnswers = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        Iterable<Question> questions = questionRepository.findByTestId(id);
        for (Question q:questions) {
            answers.addAll(answerRepository.findByQuestionId(q.getId()));
        }
        while (!answers.isEmpty()){
            int index = new Random().nextInt(answers.size());
            allAnswers.add(answers.get(index));
            answers.remove(index);
        }
        model.addAttribute("result", resultRepository.save(new Result(new Date(), testRepository.findById(id).getName(),
                testRepository.findById(id),user,2)));
        model.addAttribute("allAnswers",allAnswers);
        model.addAttribute("questions", questions);
        model.addAttribute("test",testRepository.findById(id));
        return "test";
    }

    @PostMapping("/test/addAnswer/{id}/{result_id}")
    @ResponseBody
    public void confirmAnswer(@PathVariable Integer id, @PathVariable("result_id") Integer resultId,
                              @RequestBody List<Answer> answers, @AuthenticationPrincipal UserAccount activeUser
    ) {
        Type typeQuestion = questionRepository.findById(id).getType();
        for (Answer answer : answers) {
            if (typeQuestion != Type.WORD) {
                if (answerRepository.findById(answer.getId()).isCorrect()) {
                    userAnswerRepository.save(new UserAnswer(answer.getText(), true,
                            questionRepository.findById(id), resultRepository.findById(resultId), activeUser));
                } else
                    userAnswerRepository.save(new UserAnswer(answer.getText(), false,
                            questionRepository.findById(id), resultRepository.findById(resultId), activeUser));

            } else userAnswerRepository.save(new UserAnswer(answer.getText(),
                    answer.getText().equals(answerRepository.findById(answer.getId()).getText()),
                    questionRepository.findById(id), resultRepository.findById(resultId), activeUser));
        }
        return;
    }
    @RequestMapping("/info")
    public String showTestInfo(@RequestParam String id, Model model) {
        Integer i = Integer.parseInt(id);
        Test test = testRepository.findById(i);
        if (test.getDuration() != null)
            model.addAttribute("duration", test.getDuration().toMinutes());
        else model.addAttribute("duration", 0);

        model.addAttribute("test", test);

        return "info";
    }
    @RequestMapping("/testResult/{test_id}/{result_id}")
    public String showTestResult(@PathVariable("test_id") Integer testId, @PathVariable("result_id") Integer resultId,
                                 Model model,@AuthenticationPrincipal UserAccount activeUser) {

        List<Question> questions = questionRepository.findByTestId(testId);
        int countRightAnswers = 0;
        int amountQuestions = questions.size();
        for (Question question : questions) {

            List<UserAnswer> userAnswers = userAnswerRepository.findByQuestionIdAndResultId(question.getId(), resultId);
            if (question.getType() == Type.MULTI) {
                List<Answer> answers = answerRepository.findByQuestionId(question.getId());
                int countCorrectAnswers = 0;
                int countCorrectUserAnswers = 0;
                for (Answer answer : answers) {
                    if (answer.isCorrect()) {
                        countCorrectAnswers++;
                    }
                }
                for (UserAnswer userAnswer : userAnswers) {
                    if (userAnswer.isCorrect()) {
                        countCorrectUserAnswers++;
                    } else {
                        countCorrectUserAnswers -= 100;
                    }
                }
                if (countCorrectAnswers != 0 && countCorrectUserAnswers == countCorrectAnswers) {
                    countRightAnswers++;
                }
            } else {
                for (UserAnswer userAnswer : userAnswers) {

                    if (userAnswer.isCorrect()) {
                        countRightAnswers++;
                    }
                }
            }
        }

        Result result = resultRepository.findById(resultId);
        result.setGrade(countRightAnswers / amountQuestions);
        result.setDuration(Duration.ofSeconds(new Date().getSeconds()- result.getStartTest().getSeconds()));
        model.addAttribute("countRightAnswers", countRightAnswers);
        model.addAttribute("amountQuestions", amountQuestions);
        model.addAttribute("test", testRepository.findById(testId));
        model.addAttribute("result", resultRepository.save(result));

        return "testResult";
    }
    @RequestMapping("/testResult/{result_id}")
    public String showUserResult(@PathVariable("result_id") Integer resultId,Model model){
        Result result =resultRepository.findById(resultId);
        model.addAttribute("result",result);
        return "userResult";
    }
}
