package com.thoughtworks.classstudent.BeanDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BeanWithWrittenMethods {
    @Autowired
    private BeanWithReadList beanWithReadList;

    void writeApple (){
        beanWithReadList.log(new Date().toString() + " : Apple created!");
    }

    void writeBabie(){
        beanWithReadList.log(new Date().toString() + " : Baby created!");
    }

    void writeCisco (){
        beanWithReadList.log(new Date().toString() + " : Cisco created!");
    }


}
