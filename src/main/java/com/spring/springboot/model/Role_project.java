package com.spring.springboot.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="role_first")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Role_project {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;

    public Role_project(String name){
        super();
        this.name = name;
    }
}
