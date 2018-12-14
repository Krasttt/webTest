package com.project.repositories;

import com.project.domain.Answer;
import com.project.domain.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
    Result findById(Integer id);

}
