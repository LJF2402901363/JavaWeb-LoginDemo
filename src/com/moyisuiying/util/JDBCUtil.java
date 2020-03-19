package com.moyisuiying.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.moyisuiying.dao.ResultSetHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author 陌意随影
 * @create 2020-01-23 13:44
 * @desc JDBC访问的工具类
 **/
public class JDBCUtil {
    private  static DataSource  dataSource = null;
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties"));
            try {
                dataSource = DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public static  Connection getConnection(){
    Connection connection = null;
    try {
        connection = dataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return connection;
}
public static  void close(Connection connection , Statement statement, ResultSet set){
        if (set!= null) {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    if (statement!=null){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (connection!= null){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
public  static int upDate(String sql,Object ... params){
        if (params== null) return 0;
        Connection connection = JDBCUtil.getConnection();
    try {
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        preparedStatement=connection.prepareStatement(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    for (int i = 0; i < params.length;i++){
        try {
            preparedStatement.setObject(i+1,params[i]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    int result = 0;
    try {
        result =preparedStatement.executeUpdate();
        //提交事件
        connection.commit();
        connection.setAutoCommit(true);
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            //事件回滚
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }finally {
        JDBCUtil.close(connection,preparedStatement,null);

    }
    return result;
}
public static <T> T query(String sql, ResultSetHandler<T> resultSetHandler,Object...  parms){
        if (parms==null)return  null;
        Connection connection =JDBCUtil.getConnection();
    try {
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
    try {
        preparedStatement = connection.prepareStatement(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    for (int i = 0;i < parms.length;i++){
        try {
            preparedStatement.setObject(i+1,parms[i]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    try {
        resultSet = preparedStatement.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     T t = null;
    try {
        connection.setAutoCommit(true);
         t =resultSetHandler.resultSetHandler(resultSet);
         JDBCUtil.close(connection,preparedStatement,resultSet);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return  t;
}
}
