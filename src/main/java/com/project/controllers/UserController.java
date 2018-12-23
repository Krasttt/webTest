package com.project.controllers;

import com.project.domain.Result;
import com.project.domain.UserAccount;
import com.project.repositories.ResultRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;
    @GetMapping
    public String showProfile(@AuthenticationPrincipal UserAccount user, Model model){

        List<Result> results = resultRepository.findByUserId(user.getId());
        model.addAttribute("results",results);
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/edit")
    public String userEdit(@AuthenticationPrincipal UserAccount user,@RequestParam String firstName,
                           @RequestParam String surName){
        if (!firstName.equals("")){
            user.setFirstName(firstName);
        }
        if (!surName.equals("")){
            user.setSurName(surName);
        }
        userRepository.save(user);
        return "redirect:/user";
    }


}
