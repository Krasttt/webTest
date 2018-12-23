package com.project.Sevices;

import com.project.domain.Result;
import com.project.domain.UserAccount;
import com.project.repositories.ResultRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void editUser(String firstName, String surName, UserAccount user) {
        if (!firstName.equals("")) {
            user.setFirstName(firstName);
        }
        if (!surName.equals("")) {
            user.setSurName(surName);
        }
        userRepository.save(user);
    }

    public List<Result> getResults(UserAccount user) {
        return resultRepository.findByUserId(user.getId());
    }
}
