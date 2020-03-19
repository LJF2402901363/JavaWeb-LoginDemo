package com.moyisuiying.dao;/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-23 13:40
 */

import com.moyisuiying.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-23 13:40
 * @desc 用户的JDBC访问接口
 **/
public interface UserDao extends DAO<User> {
    /**
     * @Description :通过传入的具有用户名和密码的User来获取完整信息的User对象
     * @Date 13:41 2020/1/23 0023
     * @Param * @param user ： 具有用户名和密码的User
     * @return 若该用户存在则返回一个完整信息的user对象，否则返回null
     **/
    public  User login(User user);
/**
 * @Description :通过当前页数和该页展示的数据量以及查询的条件来获取对应的用户数据
 * @Date 19:21 2020/1/27 0027
 * @Param * @param currPage
 * @param rows ：
 * @param condition
 * @return java.util.List<com.moyisuiying.domain.User>
 **/
List<User> getUserByPage(int currPage, int rows, Map<String, String> condition);
/**
 * @Description :获取数量
 * @Date 19:30 2020/1/27 0027
 * @Param * @param sql ：
 * @return int
 **/
    int getCout(String sql);
/**
 * @Description :通过id删除用户
 * @Date 11:35 2020/1/29 0029
 * @Param * @param id ：
 * @return int
 **/
    int removeUserById(int id);
}
