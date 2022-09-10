package com.sc.scourse.service.impl;

import com.sc.scourse.dao.UserDao;
import com.sc.scourse.pojo.User;
import com.sc.scourse.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = null;
    @Override
    public User login(String acount, String pwd) {
        return userDao.getUser(acount,pwd);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public void changePwdById(Integer id, String newPassword) {
        userDao.changePwdById(id,newPassword);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void updateUserById(Integer id, String acount, String pwd) {
        userDao.updateUserById(id,acount,pwd);
    }

    @Override
    public boolean addUser(String acount, String pwd, String role, Integer id) {
        if(userDao.getUserById(id)!=null){
            return false;
        }else{
            userDao.addUser(acount,pwd,role,id);
            return true;
        }
    }
}
