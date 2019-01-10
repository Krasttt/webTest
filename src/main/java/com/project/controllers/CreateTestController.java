package com.project.controllers;

import com.project.Sevices.CreateTestService;
import com.project.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateTestController {
    @Autowired
    private CreateTestService createTestService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/createtest")
    public String createTest() {
        return "createtest";
    }

    @PostMapping("/createtest")
    public String createTest(
            @RequestParam String name, @RequestParam String description,
            @RequestParam Integer duration, @AuthenticationPrincipal UserAccount user,Model model) {
        if (name.length()<5){
            model.addAttribute("nameError","Short name");
        }
        if (description.length() < 10){
            model.addAttribute("descriptionError","Short description");
        }
        if (duration<10||duration>60){
            model.addAttribute("durationError","Incorrect duration");
        }
        if (!model.asMap().isEmpty()){
            return "createtest";
        }
        createTestService.addTest(name, description, duration, user);
        return "redirect:/tests";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/addquestion")
    public String addQuestion(Model model, @RequestParam String id) {
        model.addAttribute("id",id);
        return "addQuestion";
    }


    @GetMapping("/addMultiQuestion")
    public String showAddMultiQuestion(Model model, @RequestParam String id) {
        model.addAttribute("id",id);
        return "addMultiQuestion";}

    @PostMapping("/addMultiQuestion")
    public String addMultiQuestion(@RequestParam String textQuestion, @RequestParam String id,
                                   @RequestParam(value = "check1",required = false,defaultValue = "false") String check1,
                                   @RequestParam(value ="check2" ,required = false,defaultValue = "false") String check2,
                                   @RequestParam(value ="check3",required = false,defaultValue = "false") String check3,
                                   @RequestParam(value ="check4",required = false,defaultValue = "false") String check4,
                                   @RequestParam String answer1, @RequestParam String answer2,
                                   @RequestParam String answer3, @RequestParam String answer4,Model model) {

        if (textQuestion.length()<5){
            model.addAttribute("lengthError","Short question");
            model.addAttribute("answer1",answer1);
            model.addAttribute("answer2",answer2);
            model.addAttribute("answer3",answer3);
            model.addAttribute("answer4",answer4);
            model.addAttribute("id",id);
            return "addMultiQuestion";
        }

        createTestService.addMultiQuestion(textQuestion, id, check1, check2, check3, check4, answer1, answer2, answer3, answer4);
        return "redirect:/tests";
    }

    @GetMapping("/addSingleQuestion")
    public String showSingleQuestion(Model model, @RequestParam String id) {
        model.addAttribute("id",id);
        return "addSingleQuestion";}

    @PostMapping("/addSingleQuestion")
    public String addSingleQuestion(@RequestParam String textQuestion, @RequestParam String corAnswer,
                                    @RequestParam String answer2, @RequestParam String answer3,
                                    @RequestParam String answer4, @RequestParam String id,Model model) {
        if (textQuestion.length()<5){
            model.addAttribute("lengthError","Short question");
            model.addAttribute("corAnswer",corAnswer);
            model.addAttribute("answer2",answer2);
            model.addAttribute("answer3",answer3);
            model.addAttribute("answer4",answer4);
            model.addAttribute("id",id);
            return "addSingleQuestion";
        }
        createTestService.addSingleQuestion(textQuestion, corAnswer, answer2, answer3, answer4, id);
        return "redirect:/tests";
    }

    @GetMapping("/addWordQuestion")
    public String showWordQuestion(Model model, @RequestParam String id) {
        model.addAttribute("id",id);
        return "addWordQuestion";
    }


    @PostMapping("/addWordQuestion")
    public String addWordQuestion(@RequestParam String textQuestion, @RequestParam String answer,
                                  @RequestParam String id,Model model) {
        if (textQuestion.length()<5){
            model.addAttribute("lengthError","Short question");
            model.addAttribute("answer",answer);
            model.addAttribute("id",id);
            return "addWordQuestion";
        }

        createTestService.addWordQuestion(textQuestion, answer, id);
        return "redirect:/tests";
    }


}
