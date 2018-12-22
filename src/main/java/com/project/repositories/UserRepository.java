package com.project.repositories;

import com.project.domain.UserAccount;
import com.project.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
