package com.example.restfulwebquiz.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizOptionRepository extends CrudRepository<QuizOption, Long> {
}
