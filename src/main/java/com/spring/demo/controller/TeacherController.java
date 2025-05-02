package com.spring.demo.controller;


import com.spring.demo.entity.Teacher;
import com.spring.demo.repository.TeacherRepo;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepo teacherRepo;
//    @GetMapping
//    public ResponseEntity<List<Teacher>> getAllTeachers() {
//        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.FOUND);
//    }


//    @GetMapping("/{username}")
//    public ResponseEntity<?> getTeacher(@PathVariable String username) {
//        Teacher teacherInDb = teacherService.findByUsername(username);
//        if(teacherInDb != null) {
//            return new ResponseEntity<>(teacherInDb, HttpStatus.FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }




    @PutMapping
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherName = auth.getName();
        Teacher teacherInDb = teacherService.findByUsername(teacherName);

        teacherInDb.setUsername(teacher.getUsername());
        teacherInDb.setPassword(teacher.getPassword());


        teacherService.saveNewTeacher(teacherInDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteTeacherByID() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        teacherRepo.deleteByUsername(auth.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}