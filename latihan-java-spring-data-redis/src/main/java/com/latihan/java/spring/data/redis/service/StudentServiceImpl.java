package com.latihan.java.spring.data.redis.service;

import com.latihan.java.spring.data.redis.dto.ResponseDto;
import com.latihan.java.spring.data.redis.dto.StudentDto;
import com.latihan.java.spring.data.redis.model.Student;
import com.latihan.java.spring.data.redis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ResponseDto> get() {
        List<StudentDto> result = StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(data -> StudentDto.builder().build())
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(ResponseDto.builder().data(result).build());
    }

    @Override
    public ResponseEntity<String> save(StudentDto request) {
        Student student = Student.builder()
                .id(request.getId())
                .name(request.getName())
                .grade(request.getGrade())
                .gender(request.getGender())
                .build();
        try {
            repository.save(student);
            return ResponseEntity.ok().body("SUKSES");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR");
        }
    }

    @Override
    public ResponseEntity<StudentDto> findById(String id) {
        Optional<Student> optional = repository.findById(id);
        if(optional.isPresent()){
            Student student = optional.get();
            StudentDto dto = StudentDto.builder()
                    .id(student.getId())
                    .gender(student.getGender())
                    .grade(student.getGrade())
                    .name(student.getName())
                    .build();
            return ResponseEntity.ok()
                    .body(dto);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
}
