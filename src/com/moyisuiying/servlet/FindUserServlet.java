package com.moyisuiying.servlet;

import com.moyisuiying.domain.User;
import com.moyisuiying.service.UserService;
import com.moyisuiying.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @TODO:寻找用户的servlet
 * @author: 陌意随影
 * @date: 2020-01-29 11:55
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //设置编码
        request.setCharacterEncoding("utf-8");
        //获取id
        String _id = request.getParameter("id");
        if (_id==null ){
            return;
        }
        int id = Integer.parseInt(_id);
        //查找id对应的用户
        User user =this.userService.getUserById(id);
        if (user==null){
            return;
        }
        request.setAttribute("user",user);
        request.getRequestDispatcher("/updateuser.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
