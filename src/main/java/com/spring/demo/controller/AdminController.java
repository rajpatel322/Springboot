package com.spring.demo.controller;

import com.spring.demo.entity.Teacher;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all-teacher")
    public ResponseEntity<?> getAllTeachers(){
        List<Teacher> all = teacherService.getAll();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-dean-user")
    public ResponseEntity<?> createAdminUser(@RequestBody Teacher teacher){
        teacherService.saveNewDeanOfTeacher(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
