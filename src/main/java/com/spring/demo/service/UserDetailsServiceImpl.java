package com.spring.demo.service;

import com.spring.demo.entity.Teacher;
import com.spring.demo.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = teacherRepo.findByUsername(username);
        if(teacher!= null) {
            UserDetails teacherDetail = User.builder().username(teacher.getUsername())
                    .password(teacher.getPassword())
                    .roles(teacher.getRoles().toArray(new String[0]))
                    .build();
            return teacherDetail;
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
