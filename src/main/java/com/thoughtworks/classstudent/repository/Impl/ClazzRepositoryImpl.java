package com.thoughtworks.classstudent.repository.Impl;

import com.thoughtworks.classstudent.domain.Clazz;
import com.thoughtworks.classstudent.repository.ClazzRepository;
import com.thoughtworks.classstudent.repository.storage.ClazzStorage;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ClazzRepositoryImpl implements ClazzRepository {

    @Override
    public Map<Integer, Clazz> queryAllClazz() {
        return ClazzStorage.getAllClazz();
    }

}
