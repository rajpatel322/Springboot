package com.spring.demo.service;

import com.spring.demo.entity.Teacher;
import com.spring.demo.repository.TeacherRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserDetailsServiceImplTest {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockitoBean
    private TeacherRepo teacherRepo;

    @Test
    void loadUserByUsernameTest(){
        when(teacherRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(Teacher.builder().username("Raj").password("asdasdasdsa").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Raj");
        Assertions.assertNotNull(user);
    }
}
