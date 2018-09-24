package com.thoughtworks.classstudent.BeanDemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BeanDemoTest {

    @Autowired
    BeanInvokeWrittenMethods beanInvokeWrittenMethods;

    @Autowired
    BeanWithReadList beanWithReadList;

    @Test
    void should_get_right_order() {
        beanInvokeWrittenMethods.invokeWrittenMethods();
        assertThat(beanWithReadList.logger.size()).isEqualTo(3);
    }
}