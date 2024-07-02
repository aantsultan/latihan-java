package com.latihan.java.spring.data.redis.repository;

import com.latihan.java.spring.data.redis.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
