package com.moyisuiying;

import com.moyisuiying.dao.UserDao;
import com.moyisuiying.dao.UserDaoImpl;
import com.moyisuiying.domain.PageBean;
import com.moyisuiying.domain.User;
import com.moyisuiying.service.UserServiceImpl;
import com.moyisuiying.util.Encapsulate;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-25 14:11
 * @desc 测试类
 **/
public class test {
    private Map<String, String> condition;

    @Test
    public  void testLogin(){
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setName("张三");
        user.setPassword("123");
        user =userDao.login(user);
        System.out.println(user);
    }
    @Test
    public  void testCount(){
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.getCout("select count(*)from user "));
    }
    @Test
    public  void testPage(){
        UserServiceImpl userService =new UserServiceImpl();
        PageBean<User> userByPage = userService.getUserByPage(1, 2, condition);
        System.out.println(userByPage);
    }
    @Test
    public void testUser() {
        Map<String,String>  map=new HashMap<>();
        map.put("name","root");
        map.put("password","root");
        map.put("age","21");
        map.put("qq","240290");
        map.put("email","240290@qq.com");
        map.put("address","广西");
        map.put("sex","男");
       User encapsulate = Encapsulate.encapsulate(User.class, map);
        int save = new UserDaoImpl().save(encapsulate);
        System.out.println(save);

        System.out.println(encapsulate);
    }
    }
