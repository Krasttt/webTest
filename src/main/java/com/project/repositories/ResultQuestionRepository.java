package com.project.repositories;

import com.project.domain.ResultQuestion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultQuestionRepository extends CrudRepository<ResultQuestion,Long> {
    List<ResultQuestion> findByResultId(Integer id);
}
