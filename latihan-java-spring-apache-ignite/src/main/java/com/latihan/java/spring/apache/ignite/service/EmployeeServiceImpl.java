package com.latihan.java.spring.apache.ignite.service;

import com.latihan.java.spring.apache.ignite.dto.EmployeeDto;
import com.latihan.java.spring.apache.ignite.dto.ResponseDto;
import com.latihan.java.spring.apache.ignite.model.Employee;
import com.latihan.java.spring.apache.ignite.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseEntity<ResponseDto> get(Integer id) {
        Optional<Employee> result = repository.getEmployeeById(id);
        if(result.isPresent()){
            Employee employee = result.get();
            EmployeeDto dto = EmployeeDto.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .isEmployed(employee.isEmployed())
                    .build();

            return ResponseEntity.ok()
                    .body(ResponseDto
                            .builder()
                            .data(dto)
                            .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> save(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmployed(dto.isEmployed());

        try {
            repository.save(dto.getId(), employee);
            return ResponseEntity.ok().body("SUKSES");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR");
        }

    }
}
