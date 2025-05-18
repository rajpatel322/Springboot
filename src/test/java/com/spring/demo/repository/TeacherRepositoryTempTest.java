package com.spring.demo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherRepositoryTempTest {


    @Autowired
    private TeacherRepositoryTemp teacherRepositoryTemp;


    @Test
    public void testSaveNewUser(){
         Assertions.assertNotNull(teacherRepositoryTemp.getUserForCitizen());
    }

}
