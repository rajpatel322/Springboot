package com.spring.demo.service;

import com.spring.demo.repository.TeacherRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testFindByUsername() {

        assertNotNull(teacherService.findByUsername("Raj"));
        assertNull(teacherService.findByUsername("Unknown"));

    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,6"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
