package com.project.Sevices;

import com.project.domain.Result;
import com.project.domain.UserAccount;
import com.project.repositories.ResultRepository;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ResultRepository resultRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ResultRepository resultRepository,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.resultRepository = resultRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user =userRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
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

    public boolean setAdmin(Integer id) {
        UserAccount user =userRepository.findById(id);
        if (user==null){
            return false;
        }
        else {
            user.setRole(roleRepository.findByType("admin"));
            userRepository.save(user);
            return true;
        }
    }

    public boolean editUserPassword(String curPassword, String newPassword, String repPassword, UserAccount user) {
        if(passwordEncoder.matches(curPassword, user.getPassword())&&newPassword.equals(repPassword)&&newPassword.length()>6){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
