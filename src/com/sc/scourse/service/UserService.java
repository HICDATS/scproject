package com.sc.scourse.service;

import com.sc.scourse.pojo.User;

public interface UserService {
    //
    User login(String acount , String pwd );

    User getUserById(Integer id);

    public void changePwdById(Integer id, String newPassword);

    //删除用户信息
    public void deleteUserById(Integer id);

    //根据id修改用户信息
    public void updateUserById(Integer id,String acount,String pwd);

    //添加用户
    public boolean addUser(String acount,String pwd,String role,Integer id);
}
