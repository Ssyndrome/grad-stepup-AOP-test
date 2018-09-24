package com.thoughtworks.classstudent.BeanDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanInvokeWrittenMethods {
    @Autowired
    private BeanWithWrittenMethods beanWithWrittenMethods;

    void invokeWrittenMethods(){
        beanWithWrittenMethods.writeApple();
        beanWithWrittenMethods.writeBabie();
        beanWithWrittenMethods.writeCisco();
    }
}
