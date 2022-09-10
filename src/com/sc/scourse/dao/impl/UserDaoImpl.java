package com.sc.scourse.dao.impl;

import com.sc.myssm.basedao.BaseDAO;
import com.sc.scourse.dao.UserDao;
import com.sc.scourse.pojo.User;

public class UserDaoImpl extends BaseDAO<User> implements UserDao {
    @Override
    public User getUser(String acount, String pwd) {
        return load("select * from t_user_login where acount like ? and pwd like ? " , acount , pwd );
    }

    @Override
    public void addUser(String acount,String pwd,String role,Integer id) {
        String sql = "insert into t_user_login values(?,?,?,?)";
        executeUpdate(sql,acount,pwd,role,id);
    }

    @Override
    public User getUserById(Integer id) {
        return load("select * from t_user_login where id = ?" , id );
    }

    @Override
    public void changePwdById(Integer id, String newPassword) {
        String sql = "update t_user_login set pwd = ? where id = ?";
        executeUpdate(sql,newPassword,id);
    }

    @Override
    public void deleteUserById(Integer id) {
        String sql = "delete from t_user_login where id = ?";
        executeUpdate(sql,id);
    }

    @Override
    public void updateUserById(Integer id,String acount,String pwd) {
        String sql = "update t_user_login set acount = ? , pwd = ? where id = ?";
        executeUpdate(sql,acount,pwd,id);
    }
}
