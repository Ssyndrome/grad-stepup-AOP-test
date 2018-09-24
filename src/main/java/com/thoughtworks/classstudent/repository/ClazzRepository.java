package com.thoughtworks.classstudent.repository;

import com.thoughtworks.classstudent.domain.Clazz;

import java.util.Map;

public interface ClazzRepository {

    Map<Integer, Clazz> queryAllClazz();

}
