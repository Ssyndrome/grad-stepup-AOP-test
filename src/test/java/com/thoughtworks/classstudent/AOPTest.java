package com.thoughtworks.classstudent;

import com.thoughtworks.classstudent.AOPClass.annotation.LoggerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.thoughtworks.classstudent.AOPClass.StudentService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AOPTest {

    @Autowired
    LoggerStorage logger;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        logger.clear();
    }

    @Test
    void should_run_before_as_advice_before() {
       studentService.addStudentAsBefore("Syn");
       assertIterableEquals(Arrays.asList("addStudentAsBefore", "Syn"), logger.getLogger());
    }

    @Test
    void should_run_after_as_advice_after() throws Exception {
        studentService.addStudentAsAfter("Syn");
        assertIterableEquals(Arrays.asList("Syn", "addStudentAsAfter"), logger.getLogger());
    }

    @Test
    void should_run_after_whether_exceptions_have_been_thrown() throws Exception {
        try {
            studentService.addStudentAsAfter("");
        } catch (Exception e) {
            assertIterableEquals(Arrays.asList("addStudentAsAfter"), logger.getLogger());
        }
    }

    @Test
    void should_run_around_as_advice_around() {
        studentService.addStudentAsAround("Syn");
        assertIterableEquals(Arrays.asList("addStudentAsAround", "Syn", "addStudentAsAround"), logger.getLogger());
    }

    @Test
    void should_run_return_as_advice_after_returning() {
        studentService.getStudentName("Syn");
        assertIterableEquals(Arrays.asList("getStudentName"), logger.getLogger());
    }

    @Test
    void should_not_run_without_return_value_as_advice_after_returning() throws Exception {
        try {
            studentService.throwExceptionAsNoReturn("");
        } catch (Exception e) {
            assertEquals(0, logger.getLogger().size());
        }
    }

    @Test
    void should_run_after_throw_exception_as_advice_after_throw() throws Exception {
        try {
            studentService.throwException();
        } catch (Exception e) {
            assertIterableEquals(Arrays.asList("throwException"), logger.getLogger());
        }
    }
}
