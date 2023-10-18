package com.spring.springboot.service;

import com.spring.springboot.model.Role_project;
import com.spring.springboot.model.User_project;
import com.spring.springboot.repository.UserRepository;
import com.spring.springboot.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User_project save(UserRegistrationDto userRegistrationDto) {
        User_project user_project=new User_project(
                userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
               passwordEncoder.encode(userRegistrationDto.getPassword()),
                Arrays.asList(new Role_project("ROLE_USER")));
        return  userRepository.save(user_project);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_project user_project = userRepository.findByEmail(username);
        if(user_project == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user_project.getEmail(), user_project.getPassword(), mapRolesToAuthorities(user_project.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role_project> roles){
        return roles.stream().map(role_project -> new SimpleGrantedAuthority(role_project.getName())).collect(Collectors.toList());
    }
}
