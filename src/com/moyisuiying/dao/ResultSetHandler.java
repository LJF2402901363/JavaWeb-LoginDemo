package com.moyisuiying.dao;/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-23 13:43
 */

import java.sql.ResultSet;

/**
 * @author 陌意随影
 * @create 2020-01-23 13:43
 * @desc 数据库结果集的出来接口
 **/
public interface ResultSetHandler<T> {
    public  T resultSetHandler(ResultSet resultSet);
}
