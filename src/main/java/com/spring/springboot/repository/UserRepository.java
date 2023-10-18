package com.spring.springboot.repository;

import com.spring.springboot.model.User_project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User_project, Long> {
        User_project findByEmail(String email);

}
