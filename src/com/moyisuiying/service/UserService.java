package com.moyisuiying.service;/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-25 12:32
 */

import com.moyisuiying.domain.PageBean;
import com.moyisuiying.domain.User;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-25 12:32
 * @desc User的业务逻辑接口
 **/
public interface UserService {
    /**
     * @Description :用于通过封装有用户名和用户密码的User对象来获取完整的登录用户信息
     * @Date 12:37 2020/1/25 0025
     * @Param * @param user ：
     * @return 用户存在则返回完整的信息，否则返回null
     **/
    User login(User user);
    /**
     * @Description :通过当前的页数和每页的数量以及查询的条件来获取用户
     * @Date 19:09 2020/1/27 0027
     * @Param * @param currPage
     * @param totalPage ：
     * @param condition
     * @return java.util.List<com.moyisuiying.domain.User>
     **/
    PageBean<User> getUserByPage(int currPage, int totalPage, Map<String, String> condition);
   /**
    * @Description :向数据库中添加新的用户
    * @Date 16:44 2020/1/28 0028
    * @Param * @param newuser ：
    * @return boolean
    **/
    boolean save(User newuser);
   /**
    * @Description :通过id删除用户
    * @Date 11:32 2020/1/29 0029
    * @Param * @param id ：
    * @return boolean
    **/
    boolean removeUserById(int id);
/**
 * @Description :通过id获取用户
 * @Date 11:59 2020/1/29 0029
 * @Param * @param id ：
 * @return com.moyisuiying.domain.User
 **/
    User getUserById(int id);
/**
 * @Description :更新用户数据
 * @Date 13:47 2020/1/29 0029
 * @Param * @param user ：
 * @return boolean 返回是否更新成功
 **/
    boolean upDateUSer(User user);
}
