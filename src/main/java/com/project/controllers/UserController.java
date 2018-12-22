package com.project.controllers;

import com.project.domain.Result;
import com.project.domain.UserAccount;
import com.project.repositories.ResultRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
