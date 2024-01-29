package com.latihan.java.atomikos.repository.hts;

import com.latihan.java.atomikos.model.hts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
