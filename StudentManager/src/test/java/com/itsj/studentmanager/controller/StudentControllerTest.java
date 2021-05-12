package com.itsj.studentmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsj.studentmanager.exception.StudentNotFoundException;
import com.itsj.studentmanager.model.Student;
import com.itsj.studentmanager.repository.StudentRepo;
import com.itsj.studentmanager.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
//@WithMockUser
@SuppressWarnings("unchecked")
public class StudentControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepo studentRepo;

    @Test
    public void getALLStudent() throws Exception{

        List<Student> listStudent = new ArrayList<Student>();
        {
            try {
                Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1999-06-03");
                Student student = new Student(1L,"Phuong", "phuong@gmail.com", "HaNoi", birthday );
                listStudent.add(student);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Integer id = 0;
        when(studentService.getAllStudents(id)).thenReturn(listStudent);

        String url = "/api/student/getAll/" + id;
        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String expectedJonResponse = objectMapper.writeValueAsString(listStudent);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJonResponse);
    }

    @Test
    public void testSaveStudent() throws JsonProcessingException, Exception, ParseException{
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1999-06-03");
            Student newStudent = new Student("Phuong", "phuong@gmail.com", "HaNoi", birthday );
            Student savedStudent = new Student(1L,"Phuong", "phuong@gmail.com", "HaNoi", birthday );
            when(studentService.saveStudent(newStudent)).thenReturn(savedStudent);

            String url = "/api/student/save";
            mockMvc.perform(post(url)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(newStudent))
                    .with(csrf())
            ).andExpect(status().isOk()).andExpect(content().string(""));
    }

    @Test
    public void testUpdateStudent() throws JsonProcessingException, Exception, ParseException{
        Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1999-06-03");
        Student existStudent = new Student(1L, "Duy", "duy@gmail.com", "DaNang", birthday );
        Student savedStudent = new Student(1L, "Duy", "duy@gmail.com", "DaNang", birthday );
        when(studentService.saveStudent(existStudent)).thenReturn(savedStudent);

        String url = "/api/student/save";
        mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(existStudent))
                .with(csrf())
        ).andExpect(status().isOk()).andExpect(content().string("")).andDo(print());
    }

    @Test
    public void testDeleteStudent() throws Exception{
        Integer studentId = 1;
        Mockito.doNothing().when(studentRepo).deleteById(Long.valueOf(studentId));
        String url = "/api/student/delete/1";
        mockMvc.perform(delete(url)).andExpect(status().isOk());
    }
}