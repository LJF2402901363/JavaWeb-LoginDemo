package com.moyisuiying.dao;/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-23 13:35
 */

import java.util.List;

/**
 * @author 陌意随影
 * @create 2020-01-23 13:35
 * @desc 顶级的JDBC访问接口
 **/
public interface DAO<T> {
    /**
     * @Description :像数据库中存储数据
     * @Date 13:36 2020/1/23 0023
     * @Param * @param t ： 要存储的对象
     * @return int 存储成功返回1，否则返回0
     **/
    public int save(T t);
    /**
     * @Description : 更新指定的用户
     * @Date 13:37 2020/1/23 0023
     * @Param * @param t ：要更新的对象
     * @return int  更新成功返回1，否则返回0
     **/
    public  int upDate(T t);
    /**
     * @Description :通过指定的id来获取对象
     * @Date 13:39 2020/1/23 0023
     * @Param * @param id ： 要获取的id
     * @return T 返回一个对象
     **/
    public  T  get(int id);
    /**
     * @date: 2020/1/23 0023 13:40
     * @description: 获取所有的用户数据
     * @param :
     * @return:  返回用户数据的集合
     */
    public List<T> getAll();

}
