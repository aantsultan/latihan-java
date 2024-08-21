package com.latihan.java.spring.jasperreport.repository;

import com.latihan.java.spring.jasperreport.model.WeavingSizing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeavingSizingRepository extends JpaRepository<WeavingSizing, Long> {
}
