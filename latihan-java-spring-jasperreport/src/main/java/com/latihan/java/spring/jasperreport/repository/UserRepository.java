package com.latihan.java.spring.jasperreport.repository;

import com.latihan.java.spring.jasperreport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
