package com.project.repositories;

import com.project.domain.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {
    UserAnswer findById(Integer id);
}
