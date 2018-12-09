package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    @RequestMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @RequestMapping("/authorization")
    public String authorizationPage(){
        return "authorization";
    }
}
