package com.amh.pm.dao;

import java.util.List;

import com.amh.pm.entity.User;

public interface UserDao {

    void add(User user);
    void edit(User user);
    void delete(int id);

    List<User> findAll();

    User findById(int id);
    List<User>userByName(String name, String password);
    
    User findUserIdByName(String name);
	List<User> findUserNameByOrgnId(int orgId);
}
