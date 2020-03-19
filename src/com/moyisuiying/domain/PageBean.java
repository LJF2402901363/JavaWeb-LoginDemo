package com.moyisuiying.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陌意随影
 * @create 2020-01-27 19:13
 * @desc 查询条件
 **/
public class PageBean<T> implements Serializable {
    /**当前页面*/
    private  int currPage;
    /**每页展示的数据*/
    private int rows;
    /**总页码数*/
    private int totalPage;
    /**总数*/
    private int  total;
    /**放回用户的集合*/
    private List<T> userList;

    public PageBean() {
    }

    public PageBean(int currPage,int rows, int totalPage, int total, List<T> userList) {
        this.rows=rows;
        this.currPage = currPage;
        this.totalPage = totalPage;
        this.total = total;
        this.userList = userList;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getUserList() {
        return userList;
    }

    public void setUserList(List<T> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", rows=" + rows +
                ", totalPage=" + totalPage +
                ", total=" + total +
                ", userList=" + userList +
                '}';
    }
}
