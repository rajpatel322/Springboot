package com.spring.demo.service;

import com.spring.demo.entity.Student;
import com.spring.demo.entity.Teacher;
import com.spring.demo.repository.StudentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherService teacherService;

    @Transactional
    public void saveEntry(Student student, String username) {
        try{
            Teacher teacher = teacherService.findByUsername(username);
            Student saved = studentRepo.save(student);
            teacher.getStudentList().add(saved); // add the student element to teacher's studentList
            teacherService.saveEntry(teacher); // save the teacher to the database
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entity", e);
        }

    }

    public void saveEntry(Student student) {
        studentRepo.save(student);
    }

    public List<Student> getAll(){
        return studentRepo.findAll();
    }

    public Optional<Student> findById(ObjectId id) {
        return studentRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed = false;
        try {
            Teacher teacher = teacherService.findByUsername(username);

            removed = teacher.getStudentList().removeIf(x -> x.getId().equals(id));
            if(removed){
                teacherService.saveEntry(teacher);
                studentRepo.deleteById(id);
            }

        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry", e);
        }
        return removed;

    }
}
