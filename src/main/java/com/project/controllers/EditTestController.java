package com.project.controllers;

import com.project.Sevices.EditTestService;
import com.project.domain.Answer;
import com.project.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditTestController {
    @Autowired
    private EditTestService editTestService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/editTest")
    public String showEdit(
            Model model,
            @RequestParam Integer id
    ) {

        List<Question> questions = editTestService.getQuestions(id);
        model.addAttribute("questions", questions);
        model.addAttribute("allAnswers", editTestService.getAnswers(questions));
        model.addAttribute("id", id);
        return "editTest";
    }

    @PostMapping("/editTest/answers/{id}")
    @ResponseBody
    public List<Answer> editQuestionAnswers(@PathVariable Integer id, @RequestBody List<Answer> answers) {
        editTestService.editQuestionAnswers(id, answers);
        return answers;
    }
    @PostMapping("/editTest/question/{id}")
    @ResponseBody
    public String editQuestionText  (@PathVariable Integer id, @RequestBody String questionText) {
        editTestService.editQuestionText(id,questionText);
        return questionText;
    }
}
