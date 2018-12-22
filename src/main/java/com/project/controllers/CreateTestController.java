package com.project.controllers;

import com.project.domain.*;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateTestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/createtest")
    public String createTest() {
        return "createtest";
    }

    @PostMapping("/createtest")
    public String createTest(
            @RequestParam String name, @RequestParam String description,
            @RequestParam Integer duration, @AuthenticationPrincipal UserAccount user) {

        Test test = new Test(name, description, 0, Duration.ofMinutes(duration),user);
        testRepository.save(test);
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
    public String addMultiQuestion(@RequestParam String textQuestion,
                                   @RequestParam String id,
                                   @RequestParam(value = "check1",required = false,defaultValue = "false") String check1,
                                   @RequestParam(value ="check2" ,required = false,defaultValue = "false") String check2,
                                   @RequestParam(value ="check3",required = false,defaultValue = "false") String check3,
                                   @RequestParam(value ="check4",required = false,defaultValue = "false") String check4,
                                   @RequestParam String answer1,
                                   @RequestParam String answer2,
                                   @RequestParam String answer3,
                                   @RequestParam String answer4){

        Test test = testRepository.findById(Integer.parseInt(id));
        test.setAmountQuestions(test.getAmountQuestions()+1);
        testRepository.save(test);

        Question question = new Question(textQuestion, Type.MULTI,test);
        questionRepository.save(question);

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answer1,check1.equals("true"),question));
        answers.add(new Answer(answer2,check2.equals("true"),question));
        answers.add(new Answer(answer3,check3.equals("true"),question));
        answers.add(new Answer(answer4,check4.equals("true"),question));
        answerRepository.saveAll(answers);

        return "redirect:/tests";
    }

    @GetMapping("/addSingleQuestion")
    public String showSingleQuestion(Model model, @RequestParam String id) {
        model.addAttribute("id",id);
        return "addSingleQuestion";}

    @PostMapping("/addSingleQuestion")
    public String addSingleQuestion(@RequestParam String textQuestion,
                                    @RequestParam String corAnswer,
                                    @RequestParam String answer2,
                                    @RequestParam String answer3,
                                    @RequestParam String answer4,
                                    @RequestParam String id){
        Test test = testRepository.findById(Integer.parseInt(id));
        test.setAmountQuestions(test.getAmountQuestions()+1);
        testRepository.save(test);
        Question question = new Question(textQuestion, Type.SINGLE,test);
        questionRepository.save(question);

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(corAnswer,true,question));
        answers.add(new Answer(answer2,false,question));
        answers.add(new Answer(answer3,false,question));
        answers.add(new Answer(answer4,false,question));
        answerRepository.saveAll(answers);

        return "redirect:/tests";
    }

    @GetMapping("/addWordQuestion")
    public String showWordQuestion(Model model, @RequestParam String id) {
        model.addAttribute("id",id);
        return "addWordQuestion";}

    @PostMapping("/addWordQuestion")
    public String addWordQuestion(@RequestParam String textQuestion,
                                  @RequestParam String answer,
                                  @RequestParam String id
    ){
        Test test = testRepository.findById(Integer.parseInt(id));
        test.setAmountQuestions(test.getAmountQuestions()+1);
        testRepository.save(test);

        Question question = new Question(textQuestion, Type.WORD,test);
        questionRepository.save(question);

        answerRepository.save(new Answer(answer,true,question));
        return "redirect:/tests";}





}
