package com.moyisuiying.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 陌意随影
 * @create 2020-01-25 13:22
 * @desc 配置集合的工具
 **/
public class PropertiesUtil {
    private static  Properties[] properties = new Properties[2];
    static {
        for (int i = 0;i  < properties.length;i++){
            properties[i]=new Properties();
        }
        try {
            properties[0].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties"));
            properties[1].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("table.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static String getProByName(String name) {
        if (name==null||"".equals(name)) return  null;
        for (Properties propertie:properties){
            String value = propertie.getProperty(name);
            if (value!= null ||"".equals(value)){
                return value;
            }
        }
    return  null;
    }
}
