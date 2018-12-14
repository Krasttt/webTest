package com.project.controllers;

import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.domain.Result;
import com.project.domain.Test;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.ResultRepository;
import com.project.repositories.TestRepository;
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
    final ResultRepository resultRepository;
    final TestRepository testRepository;
    final QuestionRepository questionRepository;
    final AnswerRepository answerRepository;

    public TestController(ResultRepository resultRepository,
                          TestRepository testRepository,
                          QuestionRepository questionRepository,
                          AnswerRepository answerRepository) {
        this.resultRepository = resultRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    @RequestMapping("/test")
    public String test(
            @RequestParam(defaultValue = "1") Integer id,
            Model model) {
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
        model.addAttribute("result",resultRepository.save(new Result(new Date(),testRepository.findById(id).getName(),testRepository.findById(id))));
        model.addAttribute("allAnswers",allAnswers);
        model.addAttribute("questions", questions);
        model.addAttribute("test",testRepository.findById(id));
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
    @RequestMapping("/testResult/{test_id}/{result_id}")
    public  String showResult( @PathVariable Integer test_id,@PathVariable Integer result_id,
                               Model model){

        //Проверка пройденного теста на правильность и вывод результатов на страницу


        return "testResult";
    }
}
