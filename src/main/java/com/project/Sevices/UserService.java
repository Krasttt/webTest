package com.project.Sevices;

import com.project.domain.Result;
import com.project.domain.UserAccount;
import com.project.repositories.ResultRepository;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void editUserInfo(String firstName, String surName, UserAccount user) {
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

    public List<UserAccount> getAllUsers() {
        return userRepository.findAllBy();
    }

    public UserAccount getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void setAdmin(Integer id) {
        UserAccount user =userRepository.findById(id);
        user.setRole(roleRepository.findByType("admin"));
        userRepository.save(user);
    }

    public boolean editUserPassword(String curPassword, String newPassword, String repPassword, UserAccount user) {
        if(passwordEncoder.matches(curPassword, user.getPassword())&&newPassword.equals(repPassword)){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
