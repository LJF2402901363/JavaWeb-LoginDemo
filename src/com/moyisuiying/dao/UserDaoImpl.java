package com.moyisuiying.dao;
import com.moyisuiying.domain.User;
import com.moyisuiying.util.ConfigContant;
import com.moyisuiying.util.Encapsulate;
import com.moyisuiying.util.JDBCUtil;

import javax.swing.text.html.parser.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-25 12:39
 * @desc UserDao的实现类
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public User login(User user) {
        //sql语句
        String sql = "select *from user where name=? and password=?";
        UserResultHandler userResultHandler = new UserResultHandler();
        List<User> list = JDBCUtil.query(sql,userResultHandler,user.getName(),user.getPassword());
        return list.size()==1?list.get(0):null;
    }

    @Override
    public List<User> getUserByPage(int start, int rows, Map<String, String> condition) {
        String sql = "select *from user where 1=1  ";
        StringBuilder sb=new StringBuilder(sql);
        if (condition!=null && condition.size()!=0){
            for (Map.Entry<String,String > entry:condition.entrySet()){
                String value = entry.getValue();
                if (value==null||"".equals(value)){
                    continue;
                }
                sb.append(" and ").append(entry.getKey()).append(" like ").append(" '%").append(value+"%' ");
            }
        }
        sb.append("  limit ?,?");
        List<User> list = JDBCUtil.query(sb.toString(),new UserResultHandler(),start,rows);
      if (list.size()!=0){
          return list;
      }
        return null;
    }

    @Override
    public int getCout(String sql) {
      return   JDBCUtil.query(sql, new ResultSetHandler<Integer>() {
            @Override
            public Integer resultSetHandler(ResultSet resultSet) {
                try {
                    if (resultSet.next())
                    return Integer.parseInt(resultSet.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

    }

    @Override
    public int removeUserById(int id) {
        String sql = "delete from user where id=?";
        int result = JDBCUtil.upDate(sql, id);
        return result;
    }

    @Override
    public int save(User user) {
        String sql="insert into User(name,password,sex,age,qq,email,address) values(?,?,?,?,?,?,?)";
        int result = JDBCUtil.upDate(sql,
                user.getName(), user.getPassword(),
                user.getSex(), user.getAge(),
                user.getQq(), user.getEmail(),
                user.getAddress()
        );
        return result;
    }

    @Override
    public int upDate(User user) {
        String sql="update  user set name=?,password=?,sex=?,age=?,qq=?,email=?,address=? where id=?";
        int result = JDBCUtil.upDate(sql,
                user.getName(), user.getPassword(),
                user.getSex(), user.getAge(),
                user.getQq(), user.getEmail(),
                user.getAddress(), user.getId()
        );
        return  result;
    }

    @Override
    public User get(int id) {
        String sql="select *from user where id=?";
        ResultSetHandler<User> resultSetHandler = new ResultSetHandler<User>() {
            @Override
            public User resultSetHandler(ResultSet resultSet) {
                List<User> list = new ArrayList<>();
                Encapsulate.encapsulate(resultSet,list, ConfigContant.USER_TABLE);
                return list.size()==0?null:list.get(0);
            }
        };
        User user = JDBCUtil.query(sql, resultSetHandler, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql="select *from user ";
        UserResultHandler resultSetHandler = new UserResultHandler();
        List<User> list  =  JDBCUtil.query(sql, resultSetHandler);
        return list;
    }

}
class UserResultHandler implements ResultSetHandler<List<User>>{

    @Override
    public List<User> resultSetHandler(ResultSet resultSet) {
        List<User> list = new ArrayList<>();
        Encapsulate.encapsulate(resultSet,list, ConfigContant.USER_TABLE);
        return list;
    }
}