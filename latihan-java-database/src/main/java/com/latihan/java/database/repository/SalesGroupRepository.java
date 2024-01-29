package com.latihan.java.database.repository;

import com.latihan.java.database.model.SalesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesGroupRepository extends JpaRepository<SalesGroup, Long> {
}
