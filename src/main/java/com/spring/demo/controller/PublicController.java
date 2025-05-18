package com.spring.demo.controller;

import com.spring.demo.cache.AppCache;
import com.spring.demo.entity.Email;
import com.spring.demo.entity.Teacher;
import com.spring.demo.service.EmailService;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EmailService emailService;

    @GetMapping("health-check")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/create-teacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.saveNewTeacher(teacher);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/email-sent")
    public ResponseEntity<?> sendEmail(@RequestBody Email email) {

        try {
            emailService.sendEmail(email.getTo(), email.getSubject(), email.getBody());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
