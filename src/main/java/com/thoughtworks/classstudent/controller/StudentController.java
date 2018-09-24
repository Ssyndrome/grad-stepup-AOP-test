package com.thoughtworks.classstudent.controller;

import com.thoughtworks.classstudent.domain.Student;
import com.thoughtworks.classstudent.repository.Impl.StudentRepositoryImpl;
import com.thoughtworks.classstudent.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private StudentRepository studentRepository = new StudentRepositoryImpl();

    @PostMapping("/api/students/")
    private ResponseEntity createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentRepository.createStudent(student), HttpStatus.CREATED);
    }

    @PostMapping("/api/students/clazzes/{clazzId}")
    private ResponseEntity createStudentToClazz(@RequestBody Student student,
                                                @PathVariable Integer clazzId) {
        return new ResponseEntity<>(studentRepository.createStudentToClazz(student, clazzId), HttpStatus.CREATED);
    }

}
