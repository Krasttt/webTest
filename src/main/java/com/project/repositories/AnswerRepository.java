package com.project.repositories;

import com.project.domain.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findByQuestionId(Integer id);
    Answer findById(Integer id);
}
