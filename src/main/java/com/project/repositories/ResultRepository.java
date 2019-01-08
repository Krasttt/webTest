package com.project.repositories;

import com.project.domain.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
    Result findById(Integer id);
    List<Result> findByUserId(Integer id);
    Result findFirstByTestIdAndActive(Integer testId,boolean active);
    List<Result> findByActive(boolean active);

}
