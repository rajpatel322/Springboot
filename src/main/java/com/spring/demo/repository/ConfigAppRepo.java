package com.spring.demo.repository;

import com.spring.demo.entity.ConfigApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigAppRepo extends MongoRepository<ConfigApp, ObjectId> {

}
