package com.cn.server;

import com.cn.entity.user;
import dao.UserDAO;

import java.sql.SQLException;

public class UserServies {
    public  boolean isRightLogin(user user) throws SQLException {
        UserDAO userDAO=new UserDAO();
        user dbuser=userDAO.getUser(user);
        return dbuser!=null;

    }
}
