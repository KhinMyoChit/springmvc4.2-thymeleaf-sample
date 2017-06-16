package com.amh.pm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.UserDao;
import com.amh.pm.entity.User;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public List<User> userByName(String name, String password) {
        return userDao.userByName(name, password);
    }

    public List<User> findUserNameByOrgnId(int orgId) {
        return userDao.findUserNameByOrgnId(orgId);
    }

    public User findUserIdByName(String name) {
        return userDao.findUserIdByName(name);
    }

    @Override
    public User checkValidUser(String name, String password) {
        return userDao.checkValidUser(name, password);
    }

    @Override
    @Transactional
    public List<User> getUsers(String name) {
        return userDao.getUsers(name);
    }

}
