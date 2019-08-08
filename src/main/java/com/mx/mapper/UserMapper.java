package com.mx.mapper;

import com.mx.pojo.User;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User queryUserByname(String name);

    int deleteUserByname(String name);

    int updateUserByname(User user);

    List<User> queryUser();


}