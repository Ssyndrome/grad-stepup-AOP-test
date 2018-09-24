package com.thoughtworks.classstudent.repository.Impl;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;
import com.thoughtworks.classstudent.domain.Student;
import com.thoughtworks.classstudent.repository.StudentRepository;
import com.thoughtworks.classstudent.repository.storage.ClazzStorage;
import com.thoughtworks.classstudent.repository.storage.StudentStorage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public Student createStudent(Student student) {
        StudentStorage.addStudent(student);
        return student;
    }

    @Override
    public Student createStudentToClazz(Student student, Integer clazzId) {
        student.setClazzId(clazzId);
        StudentStorage.addStudent(student);
        return student;
    }

    @Override
    public List<Student> queryStudentByClazzAndAge(int clazzId, Integer lowestAge) {
        return StudentStorage.queryStudentByClazzAndAge(clazzId, lowestAge);
    }

    @Override
    public Student addOneStudentToClazz(int clazzId, Integer id) {
        StudentStorage.addClazzToOneStudent(clazzId, id);
        return StudentStorage.getStudentById(id);
    }

    @Override
    public List<Student> queryStudentsByClazz(int clazzId) {
        return StudentStorage.getStudentsByClazzId(clazzId);
    }
}
