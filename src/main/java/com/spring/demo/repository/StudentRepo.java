package com.spring.demo.repository;

import com.spring.demo.entity.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, ObjectId> {

}
