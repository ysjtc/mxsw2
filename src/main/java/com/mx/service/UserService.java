package com.mx.service;

import com.mx.pojo.User;

import java.util.List;

public interface UserService {
    /*用户登陆*/
    public User login(User user);

    /*添加用户*/
    public boolean addUser(User user);

    /*删除用户*/
    public boolean deleteUser(String name);

    /*修改用户*/
    public boolean updateUser(User user);

    /*查找特定用户*/
    public User queryUserByname(String name);

    /*查找所有用户*/
    public List<User> queryAllUser();

}
