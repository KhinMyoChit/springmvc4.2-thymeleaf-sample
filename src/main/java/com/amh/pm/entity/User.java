package com.amh.pm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table
public class User {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Size(min=3,max=30, message= "Usrname must be atleast 3 characters!")
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Email
	@Size(min=3,max=30, message="Email must be at least 3 characters!")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@NotNull
	@Size(min=3,max=30, message="Password must be atleast 3 characters!")
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	/*@OneToMany(mappedBy = "user")
	private List<ProjectMember> project;*/
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "OrganizationMember", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "organizationId", referencedColumnName = "id"))
	private List<Organization> orgList;
	 


	public List<Organization> getOrgList() {
		return orgList;
	}


	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}


	public User() {
		super();
	}
	
	
	public User (int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public User(String name,String password){
		super();
		this.name = name;
		this.password = password;
	}

	public User(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(int id,String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	/*public List<ProjectMember> getProject() {
		return project;
	}

	public void setProject(List<ProjectMember> project) {
		this.project = project;
	}*/
	
	/*@Override
	public boolean equals(Object object){
		User user = (User)object;
		if(user.name.equals(this.name) &&
				user.email.equals(this.email) &&
				user.password.equals(password)){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return name.hashCode() + email.hashCode() + password.hashCode();
	}*/
	
	@Override
	public boolean equals(Object object){
		User user = (User)object;
		if(user.id == this.id){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return id;
	}
}
