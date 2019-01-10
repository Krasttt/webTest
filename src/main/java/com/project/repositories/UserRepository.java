package com.project.repositories;

import com.project.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
    List<UserAccount> findAllBy();

    UserAccount findById(Integer id);

}
