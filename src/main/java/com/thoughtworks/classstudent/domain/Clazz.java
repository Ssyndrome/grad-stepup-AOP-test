package com.thoughtworks.classstudent.domain;

import java.util.Arrays;

public class Clazz {
    private Integer id;
    private String name;

    public Clazz() {
    }

    public Clazz(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudent(Student... students) {
        Arrays.stream(students)
                .forEach(
                        student ->
                                student.setClazzId(this.id)
                );
    }

}
