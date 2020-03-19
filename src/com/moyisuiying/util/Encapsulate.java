package com.moyisuiying.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-25 12:47
 * @desc 通过反射来封装数据库的实体类对象
 **/
public class Encapsulate {
    public  static <T>  void encapsulate(ResultSet resultSet, Collection<T> collection,String entityPath){
        Class<?> entityObj = null;
        Object object = null;
        try {
            try {
                entityObj = Class.forName(entityPath);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            while (true) {
                try {
                    if (!resultSet.next()) break;
                    object = entityObj.newInstance();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                //获取所有的方法
                Method[] methods = entityObj.getMethods();
                //遍历每一个方法
                for (Method method:methods){
                    //获取方法的名称
                    String methodName = method.getName();
                    //找到实体类对应的set方法并为之赋值
                    if ("set".equals(methodName.substring(0,3))){
                        //获取set方法的实体类属性
                        String fileName = methodName.substring(3).toLowerCase();
                        //实体类的set方法只有一个参数,获取该参数的类型
                        String typeName = method.getParameterTypes()[0].getTypeName();
                        if ("int".equals(typeName)||"java.lang.Integer".equals(typeName)){
                            try {
                                method.invoke(object,resultSet.getInt(fileName));
                            } catch (Exception e) {
                            }
                        }else  if ("long".equals(typeName)||"java.lang.Long".equals(typeName)){
                            try {
                                method.invoke(object,resultSet.getLong(fileName));
                            } catch (Exception e) {
                            }
                        }else if ("short".equals(typeName)||"java.lang.Short".equals(typeName)){
                            try {
                                method.invoke(object,resultSet.getShort(fileName));
                            } catch (Exception e) {
                            }
                        }else if ("double".equals(typeName)||"java.lang.Double".equals(typeName)){
                            try {
                                method.invoke(object,resultSet.getDouble(fileName));
                            } catch (Exception e) {
                            }
                        }else if ("float".equals(typeName)||"java.lang.Float".equals(typeName)){
                            try {
                                method.invoke(object,resultSet.getFloat(fileName));
                            } catch (Exception e) {
                            }
                        }else if ("String".equals(typeName)||"java.lang.String".equals(typeName)){
                            try {

                                method.invoke(object,resultSet.getString(fileName));
                            } catch (Exception e) {
                            }
                        }else if ("boolean".equals(typeName)||"java.lang.Boolean".equals(typeName)){
                            try {
                                method.invoke(object,resultSet.getBoolean(fileName));
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                collection.add((T) object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (resultSet !=null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    /**
     * @Description :通过给定的字节码对象和参数映射来获取一个封装好的JavaBean对象，该对象的实体类需要符合JavaBean规范
     * @Date 16:29 2020/1/28 0028
     * @Param * @param t
     * @param pramsMap ：
     * @return V
     **/
    public  static <T extends Class,V >  V encapsulate(T objClass, Map<String ,String> pramsMap) {
        if (objClass==null|| pramsMap.size()==0){
            return  null;
        }
        V obj = null;
        try {
            Constructor constructor = objClass.getConstructor();
             obj = (V) constructor.newInstance();
            for (Map.Entry<String ,String> set:pramsMap.entrySet() ) {
                String key = set.getKey();
                String value = set.getValue();
                if (value == null){
                    continue;
                }
                //获取对象的所有方法
                Method[] methods = objClass.getMethods();
                if (methods == null || methods.length ==0){
                    return  null;
                }
                for (Method method:methods){
                    //获取每个方法的名称
                    String methodName = method.getName();
                    //获取setter方法
                    if ("set".equals(methodName.substring(0,3))){
                        //获取属性名
                        String fileName = methodName.substring(3).toLowerCase();
                       //获取方法的参数
                        String typeName = method.getParameterTypes()[0].getTypeName();
                        if (fileName.equals(key)){
                            if ("int".equals(typeName)||"java.lang.Integer".equals(typeName)){
                                method.invoke(obj, Integer.parseInt(value));
                            }else  if ("long".equals(typeName)||"java.lang.Long".equals(typeName)){
                                method.invoke(obj, Long.parseLong(value));
                            }else if ("short".equals(typeName)||"java.lang.Short".equals(typeName)){
                                method.invoke(obj, Short.parseShort(value));
                            }else if ("double".equals(typeName)||"java.lang.Double".equals(typeName)){
                                method.invoke(obj, Double.parseDouble(value));
                            }else if ("float".equals(typeName)||"java.lang.Float".equals(typeName)){
                                method.invoke(obj, Float.parseFloat(value));
                            }else if ("String".equals(typeName)||"java.lang.String".equals(typeName)){
                                method.invoke(obj, value);
                            }else if ("boolean".equals(typeName)||"java.lang.Boolean".equals(typeName)){
                                method.invoke(obj, Boolean.parseBoolean(value));
                            }else if ("byte".equals(typeName)||"java.lang.Byte".equals(typeName)){
                                method.invoke(obj, Byte.parseByte(value));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return  obj;
    }
}
