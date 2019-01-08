package com.project.controllers;

import com.project.Sevices.RegistrationService;
import com.project.domain.UserAccount;
import com.project.domain.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser( @Valid UserRegistrationForm userAccount, BindingResult bindingResult,Model model){

        if (!userAccount.getPassword().equals(userAccount.getConfirmPassword())){
            model.addAttribute("user",userAccount);
            model.addAttribute("confirmError","Passwords don't match");
            return "registration";
        }
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user",userAccount);
            return "registration";
        }
        UserAccount user = new UserAccount();
        user.setUsername(userAccount.getUsername());
        user.setFirstName(userAccount.getFirstName());
        user.setSurName(userAccount.getSurName());
        user.setPassword(userAccount.getPassword());

        if (!registrationService.addUser(user)) {
            model.addAttribute("user",userAccount);
            model.addAttribute("userError","User already exist!");
            return "registration";
        }
        return "redirect:/login";
    }

    @RequestMapping("/authorization")
    public String authorizationPage(){
        return "authorization";
    }
}
