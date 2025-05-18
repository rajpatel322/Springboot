package com.spring.demo.repository;

import com.spring.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherRepositoryTemp {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Teacher> getUserForCitizen() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.orOperator(Criteria.where("email").regex(".*@gmail.com"), Criteria.where("citizenship").is(true)));
        List<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        return teachers;
    }

}
