<%@ page import="com.moyisuiying.domain.PageBean" %>
<%@ page import="com.moyisuiying.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 陌意随影
  Date: 2020/1/24 0024
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>用户信息展示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" />
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<h2 style="text-align: center;">
    用户信息展示
</h2><br>
姓名：<input type="text" class="layui-text" id="searchname" name="name"/>
籍贯：<input type="text" class="layui-text"  id="searchaddress" name="address"/>
邮箱：<input type="email" class="layui-text" id="searchemail" name="email"/>
<button type="button"  id="search" class="layui-btn layui-btn-sm" onclick="searchUsers()" onsubmit="return false">查询</button>
<button type="button"  class="layui-btn layui-btn-sm"  onclick="window.location.href='${pageContext.request.contextPath}/addNewUser.jsp'">添加新的联系人</button>
<button type="button"  id="delBtn" onclick="delUser()" class="layui-btn layui-btn-sm">删除选中</button><br>
<table id="table" lay-filter="test"></table>
<script>
    var table;
    layui.use('table',function(){
        table= layui.table;
        table.render({
            id:"tr",
            elem: '#table',
            // height: 'full-100',
            url:'${pageContext.request.contextPath}/userListServlet',
            request: {
                pageName: 'currPage' //页码的参数名称，默认：page
                ,limitName: 'rows' //每页数据量的参数名，默认：limit
            },
            limit:12,
            page: true //开启分页
            ,cols: [[ //表头
                {type:'checkbox',  width:40,fixed: 'left'}
                ,{field: 'uid', title: '编号', width:80, sort: true, fixed: 'left'}
                ,{field: 'name', title: '用户名', width:80}
                ,{field: 'id', title: '用户id', width:80 , id:"uid", hide:true}
                ,{field: 'sex', title: '性别', width:80}
                ,{field: 'age', title: '年龄', width: 80, sort: true}
                ,{field: 'qq', title: 'QQ', width:80}
                ,{field: 'email', title: '邮箱', width: 177}
                ,{field: 'address', title: '地址', minWidth: 80}
                ,{fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
            ]]
        });

        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据

            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            //获取所选行的用户的id
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    location.href="${pageContext.request.contextPath}/delUserServlet?id="+data.id;
                });
            } else if(layEvent === 'edit'){ //编辑
                //do something
                location.href="${pageContext.request.contextPath}/findUserServlet?id="+data.id;
            }
        });
    });
    function delUser() {
        var ids="";
        var checkStatus = table.checkStatus('tr'); //tr 即为基础参数 id 对应的值
        var len=checkStatus.data.length;

        for (var i=0;i <len;i++){
            if (i==len-1){
                ids=ids+"id="+checkStatus.data[i].id;
            }else {
                ids=ids+"id="+checkStatus.data[i].id+"&";
            }

        }
        window.location.href="${pageContext.request.contextPath}/delUserServlet?"+ids;
    }

    function searchUsers() {
        window.document.getElementById("search").onclick=function () {
            var searchname=window.document.getElementById("searchname").value;
            var searchaddres=window.document.getElementById("searchaddress").value;
            var searchemail=window.document.getElementById("searchemail").value;
            table.reload('tr', {
                id:"tr",
                elem: '#table',
                // height: 'full-100',
                url:'${pageContext.request.contextPath}/userListServlet',
                request: {
                    pageName: 'currPage' //页码的参数名称，默认：page
                    ,limitName: 'rows' //每页数据量的参数名，默认：limit
                },
                limit:12,
                where: {
                    name: searchname ,// 添加查询的参数
                    address:searchaddres,
                    email:searchemail
                },
                page: true //开启分页
                ,cols: [[ //表头
                    {type:'checkbox',  width:40,fixed: 'left'}
                    ,{field: 'uid', title: '编号', width:80, sort: true, fixed: 'left'}
                    ,{field: 'name', title: '用户名', width:80}
                    ,{field: 'id', title: '用户id', width:80 , id:"uid", hide:true}
                    ,{field: 'sex', title: '性别', width:80}
                    ,{field: 'age', title: '年龄', width: 80, sort: true}
                    ,{field: 'qq', title: 'QQ', width:80}
                    ,{field: 'email', title: '邮箱', width: 177}
                    ,{field: 'address', title: '地址', minWidth: 80}
                    ,{fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
                ]]
            }); //只重载数据

        }
    }
</script>



<script type="text/html" id="toolbar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
