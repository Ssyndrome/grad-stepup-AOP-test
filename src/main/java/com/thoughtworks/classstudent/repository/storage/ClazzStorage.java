package com.thoughtworks.classstudent.repository.storage;

import com.thoughtworks.classstudent.domain.Clazz;
import com.thoughtworks.classstudent.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClazzStorage {
    private static final Map<Integer, Clazz> CLAZZES = new HashMap<>();


    public static Map<Integer, Clazz> getAllClazz() {
        return CLAZZES;
    }

    public static void clear() {
        CLAZZES.clear();
    }

    public static void addClazz(Clazz clazz) {
        CLAZZES.put(clazz.getId(), clazz);
    }

    public static Clazz getClazzById(int clazzId) {
        return CLAZZES.get(clazzId);
    }

}
