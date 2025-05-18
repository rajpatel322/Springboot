package com.spring.demo.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;

@Getter
@Setter
@Document(collection="config_app")
public class ConfigApp {

    @NonNull
    private String key;

    @NonNull
    private String value;
}

