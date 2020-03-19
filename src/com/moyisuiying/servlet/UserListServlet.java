package com.moyisuiying.servlet;

import com.alibaba.fastjson.JSON;
import com.moyisuiying.domain.PageBean;
import com.moyisuiying.domain.User;
import com.moyisuiying.service.UserService;
import com.moyisuiying.service.UserServiceImpl;
import netscape.javascript.JSObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @TODO:展示用户的servelet
 * @author: 陌意随影
 * @date: 2020-01-25 15:18
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //设置编码
        request.setCharacterEncoding("utf-8");
        //获取当前页数
        String _currPage=request.getParameter("currPage");
        //获取当前页数的数据总量
        String rows=request.getParameter("rows");
        if(_currPage==null||"".equals(_currPage)){
            return;
        }
        if(rows==null||"".equals(rows)){
            return;
        }
        int currPage=Integer.parseInt(_currPage);
        if(currPage<1){
            currPage=1;
        }
        int totalPage=Integer.parseInt(rows);
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap==null){
            return;
        }
        //获取条件搜索映射
        Map<String, String> condition=new HashMap<>();
        for (Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            String key = entry.getKey();
            if ("currPage".equals(key)||"rows".equals(key)){
                continue;
            }else {
                String[] s = entry.getValue();
                if (s!=null || s.length!=0){
                    condition.put(key,s[0]);
                }
            }
        }
        //获取所有的用户
        PageBean<User> userByPage = userService.getUserByPage(currPage, totalPage,condition);
        String pageBeanToJsonStr = this.pageBeanToJsonStr(userByPage);
        //设置响应的方式
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(pageBeanToJsonStr);
        System.out.println(pageBeanToJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    private String  pageBeanToJsonStr(PageBean<User> userByPage){
        if (userByPage==null){
            return null;
        }
        String str="{\"code\":0,"+"\"msg\":"+"\"\""+",\"count\":"+userByPage.getTotal()+",\"data\":[";
         StringBuilder sb=new StringBuilder(str);
        List<User> list=userByPage.getUserList();
        if (list==null||list.size()==0){
            sb.append("{").append("\"id\":").append("\"\",");
            sb.append("\"name\":").append("\"\",");
            sb.append("\"sex\":").append("\"\",");
            sb.append("\"qq\":").append("\"\",");
            sb.append("\"email\":").append("\"\",");
            sb.append("\"address\":").append("\"\"").append("}");
        }else {
            User user =null;
            for (int i = 0;i < list.size();i++){
                user=list.get(i);
                sb.append("{").append("\"id\":").append(user.getId()).append(",");
                sb.append("\"uid\":").append("\""+(i+1)+"\"").append(",");
                sb.append("\"name\":").append("\""+user.getName()+"\"").append(",");
                sb.append("\"sex\":").append("\""+user.getSex()+"\""  ).append(",");
                sb.append("\"qq\":").append("\""+user.getQq()+"\"").append(",");
                sb.append("\"email\":").append("\""+user.getEmail()+"\"").append(",");
                sb.append("\"age\":").append("\""+user.getAge()+"\"").append(",");
                sb.append("\"address\":").append("\""+user.getAddress()+"\"").append("}");
                if (i==list.size()-1){
                }else{
                    sb.append(",");
                }
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
