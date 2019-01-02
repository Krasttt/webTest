package com.project.repositories;

import com.project.domain.UserAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {
    UserAnswer findById(Integer id);

    List<UserAnswer> findByResultQuestionIdAndResultId(Integer questionId, Integer resultId);
}
