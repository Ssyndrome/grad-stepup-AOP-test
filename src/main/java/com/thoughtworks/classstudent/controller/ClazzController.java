package com.thoughtworks.classstudent.controller;

import com.thoughtworks.classstudent.domain.Student;
import com.thoughtworks.classstudent.repository.ClazzRepository;
import com.thoughtworks.classstudent.repository.Impl.ClazzRepositoryImpl;
import com.thoughtworks.classstudent.repository.Impl.StudentRepositoryImpl;
import com.thoughtworks.classstudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClazzController {
    @Autowired
    @Lazy
    private ClazzRepository clazzRepository;

    @Autowired
    @Lazy
    private StudentRepository studentRepository;

    @GetMapping("/api/clazzes")
    private ResponseEntity queryClazz() {
        return new ResponseEntity<>(clazzRepository.queryAllClazz(), HttpStatus.OK);
    }

    @PutMapping("/api/clazzes/{clazzId}/students")
    private ResponseEntity addStudent(@PathVariable int clazzId,
                                      @RequestBody Student student) {
        return new ResponseEntity<>(studentRepository.addOneStudentToClazz(clazzId, student.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/api/clazzes/{clazzId}/students")
    private ResponseEntity queryStudentsByClazz(@PathVariable int clazzId) {
        return new ResponseEntity<>(studentRepository.queryStudentsByClazz(clazzId), HttpStatus.OK);
    }

    @GetMapping(value = "/api/clazzes/{clazzId}/students", params = "lowestAge")
    private ResponseEntity queryStudentByClazzAndAge(@PathVariable int clazzId,
                                                     @RequestParam Integer lowestAge) {
        return new ResponseEntity<>(studentRepository.queryStudentByClazzAndAge(clazzId, lowestAge), HttpStatus.OK);
    }
}
