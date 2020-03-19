package com.moyisuiying.service;

import com.moyisuiying.dao.UserDao;
import com.moyisuiying.dao.UserDaoImpl;
import com.moyisuiying.domain.PageBean;
import com.moyisuiying.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-25 12:33
 * @desc 用户业务逻辑接口的实现类
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public PageBean<User> getUserByPage(int currPage, int rows, Map<String, String> condition) {
        int total=this.userDao.getCout("select count(*) from user");
        int totalPage=(total%rows==0)?(total/rows):(total/rows+1);
        int start = (currPage-1)*rows;
        List<User> userList = this.userDao.getUserByPage(start,rows,condition);
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setCurrPage(currPage);
        pageBean.setRows(rows);
        pageBean.setTotal(total);
        pageBean.setTotalPage(totalPage);
        pageBean.setUserList(userList);
        return  pageBean;
    }

    @Override
    public boolean save(User newuser) {
        int result = this.userDao.save(newuser);
        return result==1;
    }

    @Override
    public boolean removeUserById(int id) {
      int result=this.userDao.removeUserById(id);
      return  result==1;
    }

    @Override
    public User getUserById(int id) {
        return this.userDao.get(id);
    }

    @Override
    public boolean upDateUSer(User user) {
        int result=this.userDao.upDate(user);
        return  result==1;
    }

}
