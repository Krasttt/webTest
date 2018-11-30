package com.project.repositories;

import com.project.domain.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
    List<Test> findAllBy();

    List<Test> findByNameContaining(String name);

    Test findById(Integer id);
}

