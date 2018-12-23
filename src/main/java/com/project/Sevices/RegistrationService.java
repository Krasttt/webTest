package com.project.Sevices;

import com.project.domain.UserAccount;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(UserAccount userAccount) {
        UserAccount userFromDB = userRepository.findByUsername(userAccount.getUsername());
        if (userFromDB != null) {
            return false;
        }
        userAccount.setActive(true);
        userAccount.setRole(roleRepository.findByType("user"));
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userRepository.save(userAccount);
        return true;
    }
}
