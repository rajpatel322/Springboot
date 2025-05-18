package com.spring.demo.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "student")
public class Student {
    @Id
    private ObjectId id;

    @NonNull
    private String name;


    @NonNull
    private String major;

    private LocalDateTime date;
}
