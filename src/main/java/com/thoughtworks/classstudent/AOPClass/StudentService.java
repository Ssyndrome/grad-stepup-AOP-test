package com.thoughtworks.classstudent.AOPClass;

import com.thoughtworks.classstudent.AOPClass.annotation.*;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    LoggerStorage logger;

    @DoLoggerBeforeExecution
    public void addStudentAsBefore(String name) {
        logger.store(name);
    }

    @DoLoggerAfterExecution
    public void addStudentAsAfter(String name) throws Exception {
        if (name.isEmpty()) {
            throwException();
        } else {
            logger.store(name);
        }
    }

    @DoLoggerAroundExecution
    public void addStudentAsAround(String name) {
        logger.store(name);
    }

    @DoLoggerWhenReturn
    public String getStudentName(String name) {
        return name;
    }

    @DoLoggerWhenReturn
    public void throwExceptionAsNoReturn(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception();
        } else {
            logger.store(name);
        }
    }

    @DoLoggerWhenThrow
    public void throwException() throws Exception {
        throwExceptionAsNoReturn("");
    }
}
