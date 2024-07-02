package com.latihan.java.atomikos.repository.md;

import com.latihan.java.atomikos.model.md.SalesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesGroupRepository extends JpaRepository<SalesGroup, Long> {
}
