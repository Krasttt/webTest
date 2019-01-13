package com.project.Sevices.impl;

import com.project.Sevices.RegistrationService;
import com.project.domain.UserAccount;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addUser(UserAccount userAccount) {

        UserAccount userFromDB = userRepository.findByUsername(userAccount.getUsername());
        if (userFromDB != null) {
            return false;
        }
        userAccount.setActive(true);
        if (userRepository.findAll().isEmpty()){
            userAccount.setRole(roleRepository.findByType("admin"));
        }
        else {
            userAccount.setRole(roleRepository.findByType("user"));
        }
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userRepository.save(userAccount);
        return true;
    }
}
