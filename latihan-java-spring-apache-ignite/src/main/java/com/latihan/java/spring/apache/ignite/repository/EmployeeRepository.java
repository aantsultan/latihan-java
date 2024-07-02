package com.latihan.java.spring.apache.ignite.repository;

import com.latihan.java.spring.apache.ignite.model.Employee;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryConfig(cacheName = "employeeCache")
public interface EmployeeRepository extends IgniteRepository<Employee, Integer> {

    Optional<Employee> getEmployeeById(Integer id);

}
