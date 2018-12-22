package com.project.controllers;

import com.project.domain.UserAccount;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(UserAccount userAccount){
        UserAccount userFromDB=userRepository.findByUsername(userAccount.getUsername());
        if(userFromDB!=null){
            return "registration";
        }
        userAccount.setActive(true);
        userAccount.setRole(roleRepository.findByType("user"));
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userRepository.save(userAccount);
        return "redirect:/login";
    }

    @RequestMapping("/authorization")
    public String authorizationPage(){
        return "authorization";
    }
}
