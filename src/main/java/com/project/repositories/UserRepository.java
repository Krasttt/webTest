package com.project.repositories;

import com.project.domain.Usr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usr, Long> {
}
