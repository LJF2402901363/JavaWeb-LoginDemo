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
 * @TODO:更新用户的servlet
 * @author: 陌意随影
 * @date: 2020-01-29 11:55
 */
@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //设置编码
        request.setCharacterEncoding("utf-8");
       //获取所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap==null ||parameterMap.size()==0)return;
        Map<String, String> map=new HashMap<>();
        for (Map.Entry<String ,String[]> entry:parameterMap.entrySet()) {
            map.put(entry.getKey(),entry.getValue()[0]);
        }
        User user = Encapsulate.encapsulate(User.class, map);
        System.out.println(user);
        //调用更新数据的业务逻辑
       boolean fla =  this.userService.upDateUSer(user);
       String msg="";
       if (fla){
           msg="更新成功";
       }else {
            msg="更新失败";
       }
        request.setAttribute("msg",msg);
        response.sendRedirect(request.getContextPath()+"/userlist.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
