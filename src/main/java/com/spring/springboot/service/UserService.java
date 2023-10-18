package com.spring.springboot.service;

import com.spring.springboot.model.User_project;
import com.spring.springboot.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User_project save(UserRegistrationDto userRegistrationDto );
}
