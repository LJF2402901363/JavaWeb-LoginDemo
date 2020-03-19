package com.moyisuiying.domain;

import java.io.Serializable;

/**
 * @author 陌意随影
 * @create 2020-01-23 12:44
 * @desc 用户类
 **/
public class User implements Serializable {
    private int id;
    private String name;
    private  String password;
    private  String sex;
    private String qq;
    private String email;
    private String address;
    private  int age;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public User() {
    }

    public User(int id, String name, String password, String sex, int age,String qq, String email, String address) {
        this.id = id;
        this.age=age;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.qq = qq;
        this.email = email;
        if (address!=null){
            this.address = address.trim();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address!=null){
            this.address = address.trim();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
