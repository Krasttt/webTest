package com.project.controllers;

import com.project.Sevices.EditTestService;
import com.project.domain.Answer;
import com.project.domain.Question;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/editTest")
public class EditTestController {

    private final EditTestService editTestService;

    public EditTestController(EditTestService editTestService) {
        this.editTestService = editTestService;
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping()
    public String showEdit(
            Model model,
            @RequestParam Integer id
    ) {

        List<Question> questions = editTestService.getQuestions(id);
        model.addAttribute("questions", questions);
        model.addAttribute("allAnswers", editTestService.getAnswers(questions));
        model.addAttribute("id", id);
        return "createEditTest/editTest";
    }

    @PostMapping("/answers/{id}")
    @ResponseBody
    public List<Answer> editQuestionAnswers(@PathVariable Integer id, @RequestBody List<Answer> answers) {
        editTestService.editQuestionAnswers(id, answers);
        return answers;
    }
    @PostMapping("/question/{id}")
    @ResponseBody
    public String editQuestionText  (@PathVariable Integer id, @RequestBody String questionText) {
        editTestService.editQuestionText(id,questionText);
        return questionText;
    }

    @GetMapping("/delete/{testId}/{questionId}")
    @Transactional
    public String deleteQuestion(@PathVariable Integer testId,@PathVariable Integer questionId){
        editTestService.deleteQuestion(testId,questionId);
        return "redirect:/editTest?id="+testId;
    }
}

