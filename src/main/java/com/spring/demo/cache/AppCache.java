package com.spring.demo.cache;

import com.spring.demo.entity.ConfigApp;
import com.spring.demo.repository.ConfigAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppCache {

    @Autowired
    private ConfigAppRepo configAppRepo;

    public Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigApp> list = configAppRepo.findAll();
        for(ConfigApp a : list){
            appCache.put(a.getKey(), a.getValue());
        }
    }
}
