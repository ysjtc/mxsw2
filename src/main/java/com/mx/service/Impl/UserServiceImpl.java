package com.mx.service.Impl;

import com.mx.mapper.UserMapper;
import com.mx.pojo.User;
import com.mx.service.UserService;
import com.mx.utils.MD5.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;

    @Override
    /*登陆*/
    public User login(User u) {
        /*获取用户记录*/
        /*获取用户记录前，先将密码加密后在与数据库中的密码比对*/
        MD5 md5 = new MD5();
        String passwd=md5.MD5Encode(u.getPassword());
        u.setPassword(passwd);
        User user=usermapper.queryUserByname(u.getName());
        if(user.getPassword().equals(u.getPassword())){
            return user;
        }else{
            return null;
        }
    }

    /*注册*/
    @Override
    public boolean addUser(User user) {
        /*判断账号是否已存在，不存在添加成功，存在则添加失败*/
        if(usermapper.queryUserByname(user.getName())==null){
            /*密码加密处理*/
            MD5 md5 = new MD5();
            String passwd=md5.MD5Encode(user.getPassword());
            user.setPassword(passwd);

            /*添加数据*/
            usermapper.insertSelective(user);
            return true;
        }else{
            return false;
        }
    }

    /*删除用户*/
    @Override
    public boolean deleteUser(String name) {
        User user=usermapper.queryUserByname(name);
        /*查看用户是否存在，若不存在则删除失败，存在则成功*/
        if(user!=null){
            usermapper.deleteUserByname(name);
            return true;
        }else{
            return false;
        }
    }

    /*修改用户*/
    @Override
    public boolean updateUser(User user) {
        User u=usermapper.queryUserByname(user.getName());
        /*查看用户是否存在，若不存在则修改失败，存在则成功*/
        if(u!=null){
            /*修改密码时需对密码进行加密处理*/
            if(user.getPassword()!=null){
                MD5 md5 = new MD5();
                String passwd=md5.MD5Encode(u.getPassword());
                u.setPassword(passwd);
            }
        usermapper.updateUserByname(user);
            return true;
        }else{
            return false;
        }
    }

    /*依据账号查找用户*/
    @Override
    public User queryUserByname(String name) {
        User user=usermapper.queryUserByname(name);
        return user;
    }

    /*查找所有用户*/
    @Override
    public List<User> queryAllUser() {
        List<User> userlist=usermapper.queryUser();
        for(int i=0;i<userlist.size();i++){
            userlist.get(i).setPassword(null);
        }
        return userlist;
    }
}
