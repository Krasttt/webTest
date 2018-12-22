package com.project.controllers;

import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EditTestController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/editTest")
    public String showEdit(
            Model model,
            @RequestParam Integer id
    ) {
        List<Answer> allAnswers = new ArrayList<>();
        Iterable<Question> questions = questionRepository.findByTestId(id);

        for (Question q : questions) {
            allAnswers.addAll(answerRepository.findByQuestionId(q.getId()));
        }
        model.addAttribute("allAnswers", allAnswers);
        model.addAttribute("questions", questions);
        model.addAttribute("id", id);
        return "editTest";
    }
    @PostMapping("/editTest/{id}")
    @ResponseBody
    public List<Answer> editQuestion(@PathVariable Integer id, @RequestBody List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getText().equals("")) {
                answer.setText(answerRepository.findById(answer.getId()).getText());
            }
            answer.setQuestion(questionRepository.findById(id));
        }
        answerRepository.saveAll(answers);
        return answers;
    }
}

