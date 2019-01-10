package com.project.controllers;

import com.project.Sevices.UserService;
import com.project.domain.Result;
import com.project.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@PreAuthorize("hasAuthority('admin')")
@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("user/{id}")
    public String showProfile(@PathVariable Integer id, Model model){

        UserAccount user = userService.getUser(id);
        List<Result> results = userService.getResults(user);

        model.addAttribute("results",results);
        model.addAttribute("user",user);
        return "profile";
    }
    @GetMapping("/usersList")
    public String showUsers(Model model){
        List<UserAccount> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "usersList";
    }

    @RequestMapping("/setAdmin/{id}")
    public String setAdmin(@PathVariable Integer id){
        userService.setAdmin(id);
        return "redirect:/usersList";
    }
}
