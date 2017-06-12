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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=3,max=30, message="Size will be between 4 and 30!")
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@NotNull
	@Column(name="description", nullable=false, unique=false)
	private String description;
	
	@ManyToOne
	private User owner;
	
	
	
	public Organization(String name, String description, User owner) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "OrganizationMember", joinColumns = @JoinColumn(name = "organizationId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
	private List<User> userList;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Organization(){
		super();
	}
	public Organization(int id, String name, String description,User owner){
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	
	public Organization(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public void addUser(User u) {
		userList.add(u);
		
	}
	
	/*@Override
	public boolean equals(Object object){
		Organization organization = (Organization)object;
		if(organization.name.equals(this.name) &&
				organization.description.equals(this.description) &&
				organization.owner.equals(this.owner)){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return name.hashCode() + description.hashCode() + owner.hashCode();
	}*/
	
	
}
