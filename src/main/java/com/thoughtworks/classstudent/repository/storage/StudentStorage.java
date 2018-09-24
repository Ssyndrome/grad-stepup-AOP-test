package com.thoughtworks.classstudent.repository.storage;

import com.thoughtworks.classstudent.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentStorage {
    private static final Map<Integer, Student> STUDENTS = new HashMap<>();

    public static Student getStudentById(int studentId) {
        return STUDENTS.get(studentId);
    }

    public static void addClazzToOneStudent(int clazzId, int studentId) {
        getStudentById(studentId).setClazzId(clazzId);
    }

    public static void addStudent(Student student) {
        STUDENTS.put(student.getId(), student);
    }

    public static List<Student> getStudentsByClazzId(int clazzId) {
        return STUDENTS.values().stream()
                .filter(student -> student.getClazzId() == clazzId)
                .collect(Collectors.toList());
    }

    public static List<Student> queryStudentByClazzAndAge(int clazzId, int lowestAge) {
        return getStudentsByClazzId(clazzId).stream()
                .filter(student -> student.getAge() > lowestAge)
                .collect(Collectors.toList());
    }
}
