package com.ekinsol.challenge.apiservice.repository;

import com.ekinsol.challenge.apiservice.jpa.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
}
