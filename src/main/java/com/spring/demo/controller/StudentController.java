package com.spring.demo.controller;

import com.spring.demo.entity.Student;
import com.spring.demo.entity.Teacher;
import com.spring.demo.service.StudentService;
import com.spring.demo.service.TeacherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;
    @GetMapping
    public ResponseEntity<?> getAllStudentofTeacher() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherName = auth.getName();
        Teacher teacher = teacherService.findByUsername(teacherName);
        List<Student> list = teacher.getStudentList();

        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String teacherName = auth.getName();
            student.setDate(LocalDateTime.now());
            studentService.saveEntry(student, teacherName);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<Student> getClassEntryById(@PathVariable ObjectId myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherName = auth.getName();
        Teacher t = teacherService.findByUsername(teacherName);
        List<Student> collect = t.getStudentList().stream().filter(x -> x.getId().equals(myId)).toList();
        if(!collect.isEmpty()){
            Optional<Student> ret = studentService.findById(myId);

            if(ret.isPresent()) {
                return new ResponseEntity<>(ret.get(), HttpStatus.FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteClassEntryById(@PathVariable ObjectId myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherName = auth.getName();
        boolean result =  studentService.deleteById(myId, teacherName);
        if(result){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateClassEntryById(@PathVariable ObjectId id,  @RequestBody Student myEntry ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherName = auth.getName();
        Teacher t = teacherService.findByUsername(teacherName);
        List<Student> collect = t.getStudentList().stream().filter(x -> x.getId().equals(id)).toList();
        if(!collect.isEmpty()){
            Optional<Student> ret = studentService.findById(id);

            if(ret.isPresent()) {
                Student s = ret.get();
                s.setName(myEntry.getName()!=null && !myEntry.getName().equals("") ? myEntry.getName() : s.getName());
                s.setMajor(myEntry.getMajor()!=null && !myEntry.getMajor().equals("") ? myEntry.getMajor() : s.getMajor());
                studentService.saveEntry(s);
                return new ResponseEntity<>(s, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}