package com.moyisuiying.servlet;

import com.moyisuiying.service.UserService;
import com.moyisuiying.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @TODO:删除信息的servlet
 * @author: 陌意随影
 * @date: 2020-01-28 20:58
 */
@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        String[] ids = request.getParameterValues("id");
        String msg="";
        if (ids==null||ids.length==0){
            return;
        }
        for (String _id:ids){
            if (_id.trim().length()==0){
                continue;
            }
            //获取id
            int id=Integer.parseInt(_id);
            //调用删除的业务逻辑
            boolean fla = this.userService.removeUserById(id);
            //判断是否删除成功
            if (fla){
                //删除成功
                msg ="删除成功！";
            }else {
                //删除失败
                msg ="失败成功！";
            }
        }
        request.setAttribute("msg",msg);
       response.sendRedirect(request.getContextPath()+"/userlist.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
