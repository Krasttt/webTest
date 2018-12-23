package com.project.controllers;

import com.project.Sevices.RegistrationService;
import com.project.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(UserAccount userAccount){
        if (!registrationService.addUser(userAccount)) {
            return "registration";
        }
        return "redirect:/login";
    }

    @RequestMapping("/authorization")
    public String authorizationPage(){
        return "authorization";
    }
}
