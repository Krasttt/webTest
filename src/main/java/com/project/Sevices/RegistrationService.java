package com.project.Sevices;

import com.project.domain.UserAccount;
import com.project.repositories.RoleRepository;
import com.project.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addUser(UserAccount userAccount) {
        if (roleRepository.count()==0){
            //TODO execute sql
        }
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
