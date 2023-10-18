package com.spring.springboot.model;


import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name =  "user_first", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class User_project {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")   //set the name in database
	private String lastName;
	
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  //cascade=CascadeType.ALL update the all changes in child class which are changes in parent class
	@JoinTable(
			name = "userRole",
			joinColumns = @JoinColumn( name = "userId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))

	private Collection<Role_project> roles;

	public User_project(String firstName, String lastName, String email, String password, Collection<Role_project> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role_project> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role_project> roles) {
		this.roles = roles;
	}

	public User_project() {
	}
}
