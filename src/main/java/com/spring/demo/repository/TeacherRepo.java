package com.spring.demo.repository;

import com.spring.demo.entity.Teacher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepo extends MongoRepository<Teacher, ObjectId> {
    Teacher findByUsername(String Username); // automatically create a query to return a Teacher object given username

    void deleteByUsername(String Username);
}
