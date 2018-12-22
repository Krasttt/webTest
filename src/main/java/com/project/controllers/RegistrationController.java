package com.project.controllers;

import com.project.domain.UserAccount;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


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
        userRepository.save(userAccount);
        return "redirect:/login";
    }

    @RequestMapping("/authorization")
    public String authorizationPage(){
        return "authorization";
    }
}
