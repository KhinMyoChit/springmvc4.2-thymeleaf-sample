package com.amh.pm.service;

import java.util.List;

import com.amh.pm.entity.User;

public interface UserService {

    void add(User user);
    void edit(User user);
    void delete(int id);

    List<User> findAll();

    User findById(int id);
	List<User>userByName(String name, String password);
	
	List<User> findUserNameByOrgnId(int orgid);
	User findUserIdByName(String name);

}
