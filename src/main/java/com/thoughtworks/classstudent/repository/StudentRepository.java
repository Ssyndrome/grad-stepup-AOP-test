package com.thoughtworks.classstudent.repository;

import com.thoughtworks.classstudent.domain.Student;

import java.util.List;

public interface StudentRepository {
    Student createStudent(Student student);

    Student createStudentToClazz(Student student, Integer clazzId);

    List<Student> queryStudentByClazzAndAge(int clazzId, Integer lowestAge);

    Student addOneStudentToClazz(int clazzId, Integer id);

    List<Student> queryStudentsByClazz(int clazzId);
}
