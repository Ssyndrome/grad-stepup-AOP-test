package com.thoughtworks.classstudent.BeanDemo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeanWithReadList {
    List<String> logger = new ArrayList<>();

    void log(String message) {
        logger.add(message);
    }
}
