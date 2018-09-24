package com.thoughtworks.classstudent.AOPClass.annotation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoggerStorage {
    private List<String> LOGGER_STORAGE = new ArrayList<>();

    public void store(String message) {
        LOGGER_STORAGE.add(message);
    }

    public List<String> getLogger() {
        return LOGGER_STORAGE;
    }

    public void clear() {
        LOGGER_STORAGE.clear();
    }
}
