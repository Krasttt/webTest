package com.project.controllers;

import com.project.Sevices.impl.RegistrationServiceImpl;
import com.project.domain.UserAccount;
import com.project.domain.UserRegistrationForm;
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
    private static final String USER_ATTRIBUTE_NAME="user";

    private final RegistrationServiceImpl registrationServiceImpl;

    public RegistrationController(RegistrationServiceImpl registrationServiceImpl) {
        this.registrationServiceImpl = registrationServiceImpl;
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String addUser( @Valid UserRegistrationForm userAccount, BindingResult bindingResult,Model model){

        if (!userAccount.getPassword().equals(userAccount.getConfirmPassword())){
            model.addAttribute(USER_ATTRIBUTE_NAME,userAccount);
            model.addAttribute("confirmError","Passwords don't match");
            return "auth/registration";
        }
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute(USER_ATTRIBUTE_NAME,userAccount);
            return "auth/registration";
        }
        UserAccount user = new UserAccount();
        user.setUsername(userAccount.getUsername());
        user.setFirstName(userAccount.getFirstName());
        user.setSurName(userAccount.getSurName());
        user.setPassword(userAccount.getPassword());

        if (!registrationServiceImpl.addUser(user)) {
            model.addAttribute(USER_ATTRIBUTE_NAME,userAccount);
            model.addAttribute("userError","User already exist!");
            return "auth/registration";
        }
        return "redirect:/login";
    }

    @RequestMapping("/authorization")
    public String authorizationPage(){
        return "auth/authorization";
    }
}
