package com.latihan.java.spring.postgresql.jsonb.repository;

import com.latihan.java.spring.postgresql.jsonb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
