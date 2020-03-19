package com.moyisuiying.servlet;

import com.moyisuiying.domain.User;
import com.moyisuiying.service.UserService;
import com.moyisuiying.service.UserServiceImpl;
import com.moyisuiying.util.Encapsulate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @TODO:添加新的用户的servlet
 * @author: 陌意随影
 * @date: 2020-01-28 15:12
 */
@WebServlet("/addNewUserServlet")
public class AddNewUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
       //获取所有的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //转换参数map
        Map<String, String> map =new HashMap<>();
        for (Map.Entry<String,String[]> entry:parameterMap.entrySet()){
            map.put(entry.getKey(),entry.getValue()[0]);
        }
        User newuser = Encapsulate.encapsulate(User.class, map);
        //调用UserService的业务逻辑将该用户添加到数据库中去
        boolean fla =this.userService.save(newuser);
        String msg ="";
        if (fla == true){
            //添加成功
            msg="添加成功！";
        }else {
            //添加失败
            msg="添加失败！";
        }
        request.setAttribute("msg",msg);
        request.getRequestDispatcher("/userlist.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
