package com.cn.action;

import com.cn.entity.user;
import com.cn.server.UserServies;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "LonginServlet", urlPatterns ="/longin")
public class LonginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map=request.getParameterMap();
        user user=new user();
        UserServies userServies=new UserServies();
        try {
            BeanUtils.populate(user,map);
            if(userServies.isRightLogin(user)){
                HttpSession session=request.getSession();
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            }else{
                request.setAttribute("msg", "用户名或密码错误");
                request.getRequestDispatcher("/longin.jsp").forward(request, response);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
