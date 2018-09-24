package com.thoughtworks.classstudent.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.classstudent.domain.Clazz;
import com.thoughtworks.classstudent.domain.Student;
import com.thoughtworks.classstudent.repository.storage.ClazzStorage;
import com.thoughtworks.classstudent.repository.storage.StudentStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClazzControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Clazz originalClazz;
    private Student originalStudent;
    private Student anotherStudent;

    @BeforeEach
    void setUp() {
        ClazzStorage.clear();
        mockMvc = webAppContextSetup(webApplicationContext).build();
        originalClazz = new Clazz(1, "11");
        originalStudent = new Student(1, "xu ya", 12);
        anotherStudent = new Student(2, "ya ya", 90);
        ClazzStorage.addClazz(originalClazz);
    }

    @Test
    void should_query_clazz_successfully() throws Exception {
        mockMvc.perform(get("/api/clazzes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.1.name").value("11"));
    }

    @Test
    void should_add_a_student_to_a_clazz() throws Exception {
        StudentStorage.addStudent(originalStudent);
        mockMvc.perform(put("/api/clazzes/1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(originalStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("xu ya"))
                .andExpect(jsonPath("$.age").value("12"))
                .andExpect(jsonPath("$.clazzId").value("1"));
    }

    @Test
    void should_query_one_clazz_students() throws Exception {
        StudentStorage.addStudent(originalStudent);
        StudentStorage.addStudent(anotherStudent);
        StudentStorage.getStudentById(1).setClazzId(1);
        StudentStorage.getStudentById(2).setClazzId(1);
        ClazzStorage.getClazzById(1).setStudent(originalStudent, anotherStudent);

        mockMvc.perform(get("/api/clazzes/1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("xu ya"))
                .andExpect(jsonPath("$[0].age").value("12"))
                .andExpect(jsonPath("$[0].clazzId").value("1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("ya ya"))
                .andExpect(jsonPath("$[1].age").value("90"))
                .andExpect(jsonPath("$[1].clazzId").value("1"));

    }

    @Test
    void should_query_students_age_bigger_than_20() throws Exception {
        StudentStorage.addStudent(originalStudent);
        StudentStorage.addStudent(anotherStudent);
        StudentStorage.getStudentById(1).setClazzId(1);
        StudentStorage.getStudentById(2).setClazzId(1);
        ClazzStorage.getClazzById(1).setStudent(originalStudent, anotherStudent);

        mockMvc.perform(get("/api/clazzes/1/students").param("lowestAge", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("ya ya"))
                .andExpect(jsonPath("$[0].age").value("90"))
                .andExpect(jsonPath("$[0].clazzId").value("1"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
