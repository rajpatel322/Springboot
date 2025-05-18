package com.spring.demo.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "teachers")
@Data // added getter and setters
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    private String email;
    private Boolean citizenship;

    @NonNull
    private String password;

    @DBRef // similar to foregin key in SQL
    private List<Student> studentList = new ArrayList<>();

    private List<String> roles;

}
