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
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-25 11:15
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private UserService userService = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //设置编码
        request.setCharacterEncoding("utf-8");
        //获取用户输入的验证码
        String checkcode = request.getParameter("checkcode");
        //获取网页生成的验证码
        String checkcode1 = (String) request.getSession().getAttribute("checkcode");
        if (checkcode==null ||"".equalsIgnoreCase(checkcode)){
            //验证码为空
            request.setAttribute("checkcode_err","验证码不能为空");
           request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if (checkcode1.equalsIgnoreCase(checkcode)){
            //验证码正确
            //获取用户提交的用户名
            String name = request.getParameter("username");
            //获取用户的密码
            String password = request.getParameter("password");
            //封装对象
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            this.userService = new UserServiceImpl();
            //获取用户的完整信息对象用于登录
            User loginUser=this.userService.login(user);
            //判断是否登录成功
            if (loginUser == null){
                //用户名或密码错误登录失败
                request.setAttribute("login_err","用户名或错误");
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                //登录成功
                response.sendRedirect(request.getContextPath()+"/userlist.jsp");
            }
        }else{
            //验证码错误
            request.setAttribute("checkcode_err","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
