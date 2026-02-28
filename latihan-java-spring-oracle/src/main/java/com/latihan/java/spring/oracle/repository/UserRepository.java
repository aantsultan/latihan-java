package com.latihan.java.spring.oracle.repository;

import com.latihan.java.spring.oracle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
