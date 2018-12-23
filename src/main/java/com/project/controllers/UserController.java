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

    @PostMapping("/edit")
    public String userEdit(@AuthenticationPrincipal UserAccount user,@RequestParam String firstName,
                           @RequestParam String surName){
        userService.editUser(firstName, surName, user);
        return "redirect:/user";
    }


}
