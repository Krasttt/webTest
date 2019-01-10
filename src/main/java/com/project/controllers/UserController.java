package com.project.controllers;

import com.project.Sevices.UserService;
import com.project.domain.Result;
import com.project.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String showProfile(@AuthenticationPrincipal UserAccount user, Model model){

        List<Result> results = userService.getResults(user);

        model.addAttribute("results",results);
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/editInfo")
    public String userEditInfo(@AuthenticationPrincipal UserAccount user,@RequestParam String firstName,
                           @RequestParam String surName,Model model){
        if(!firstName.isEmpty()&&firstName.length()<3){
            model.addAttribute("firstNameError","Min 3 letters");
        }
        if (!surName.isEmpty()&&surName.length()<3){
            model.addAttribute("surNameError","Min 3 letters");
        }
        if (!model.asMap().isEmpty()){

            List<Result> results = userService.getResults(user);
            model.addAttribute("results",results);
            model.addAttribute("user",user);
            return "profile";
        }
        userService.editUserInfo(firstName, surName, user);
        return "redirect:/user";
    }

    @PostMapping("/editPassword")
    public String userEditPassword(@AuthenticationPrincipal UserAccount user,@RequestParam String curPassword,
                           @RequestParam String newPassword,@RequestParam String repPassword,Model model){

       if (!userService.editUserPassword(curPassword, newPassword,repPassword, user)){
           model.addAttribute("passwordError","Error.Try again...");
           List<Result> results = userService.getResults(user);
           model.addAttribute("results",results);
           model.addAttribute("user",user);
           return "profile";
       }
        return "redirect:/user";
    }


}
