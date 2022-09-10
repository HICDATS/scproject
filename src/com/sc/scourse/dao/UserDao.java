package com.sc.scourse.dao;

import com.sc.scourse.pojo.User;

public interface UserDao {
    User getUser(String acount, String pwd);

    //添加用户
    void addUser(String acount,String pwd,String role,Integer id);

    User getUserById(Integer id);

    void changePwdById(Integer id , String newPassword);

    //删除用户信息
    public void deleteUserById(Integer id);

    //根据id修改用户信息
    public void updateUserById(Integer id,String acount,String pwd);


}
