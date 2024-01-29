package com.latihan.java.multiple.database.repository.hts;

import com.latihan.java.multiple.database.model.hts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
