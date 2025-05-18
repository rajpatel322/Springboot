package com.spring.demo.entity;


import lombok.Data;

@Data // added getter and setters
public class Email {

    private String to;
    private String subject;
    private String body;
}
