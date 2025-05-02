package com.spring.demo.controller;

import com.spring.demo.entity.Teacher;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("health-check")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/create-teacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        teacherService.saveNewTeacher(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
