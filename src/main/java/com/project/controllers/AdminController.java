package com.project.controllers;

import com.project.Sevices.impl.UserServiceImpl;
import com.project.domain.Result;
import com.project.domain.UserAccount;
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

    private static final String USER_ATTRIBUTE_NAME="user";

    private final UserServiceImpl userServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("user/{id}")
    public String showProfile(@PathVariable Integer id, Model model){

        UserAccount user = userServiceImpl.getUser(id);
        List<Result> results = userServiceImpl.getResults(user);

        model.addAttribute("results",results);
        model.addAttribute(USER_ATTRIBUTE_NAME,user);
        return "user/profile";
    }
    @GetMapping("/usersList")
    public String showUsers(Model model){
        List<UserAccount> users = userServiceImpl.getAllUsers();
        model.addAttribute("users",users);
        return "user/usersList";
    }

    @RequestMapping("/setAdmin/{id}")
    public String setAdmin(@PathVariable Integer id){
        userServiceImpl.setAdmin(id);
        return "redirect:/usersList";
    }
}
