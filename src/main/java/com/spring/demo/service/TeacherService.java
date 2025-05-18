package com.spring.demo.service;

import com.spring.demo.entity.Student;
import com.spring.demo.entity.Teacher;
import com.spring.demo.repository.TeacherRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    public void saveNewDeanOfTeacher(Teacher teacher){

        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setRoles(Arrays.asList("TEACHER", "DEAN"));
        teacherRepo.save(teacher);
    }

    public boolean saveNewTeacher(Teacher teacher) {
        try{
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacher.setRoles(Arrays.asList("TEACHER"));
            teacherRepo.save(teacher);
            return true;
        } catch (Exception e) {
            log.error("Error occurred ", e);
            throw new RuntimeException(e);
        }

    }

    public List<Teacher> getAll(){
        return teacherRepo.findAll();
    }

    public Optional<Teacher> findById(ObjectId id) {
        return teacherRepo.findById(id);
    }

    public void deleteById(ObjectId id) {
        teacherRepo.deleteById(id);
    }

    public Teacher findByUsername(String username) {
        return teacherRepo.findByUsername(username);
    }

}
