package com.project.controllers;

import com.project.domain.*;
import com.project.repositories.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class TestController {
    private final ResultRepository resultRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;

    public TestController(ResultRepository resultRepository,
                          TestRepository testRepository,
                          QuestionRepository questionRepository,
                          AnswerRepository answerRepository,
                          UserAnswerRepository userAnswerRepository) {
        this.resultRepository = resultRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userAnswerRepository = userAnswerRepository;
    }


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
                testRepository.findById(id),user)));
        model.addAttribute("allAnswers",allAnswers);
        model.addAttribute("questions", questions);
        model.addAttribute("test",testRepository.findById(id));
        return "test";
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
        model.addAttribute("countRightAnswers", countRightAnswers);
        model.addAttribute("amountQuestions", amountQuestions);
        model.addAttribute("test", testRepository.findById(testId));
        model.addAttribute("result", resultRepository.save(result));

        return "testResult";
    }
}
