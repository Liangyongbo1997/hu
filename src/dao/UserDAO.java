package dao;

import com.cn.entity.user;
import com.cn.util.util;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;

public class UserDAO {
    public user getUser(user user) throws SQLException {
        Connection connection= util.getConnect();
//        Statement statement=connection.createStatement();
        String sql="SELECT * FROM Book WHERE Upwd = ?AND Uname = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getUser());
        preparedStatement.setString(2,user.getPwd());
        ResultSet resultSet = preparedStatement.executeQuery();
        user dbUser=null;
        if (resultSet.next()){
            dbUser=new user();
            dbUser.setPwd(resultSet.getString("Upwd"));
            dbUser.setUser(resultSet.getString("Uname"));

        }
        util.close(resultSet);
        util.close(preparedStatement);
        util.close(connection);
        return  dbUser;
    }
}
