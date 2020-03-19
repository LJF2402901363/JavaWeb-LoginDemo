<%--
  Created by IntelliJ IDEA.
  User: 陌意随影
  Date: 2020/1/29 0029
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更改用户信息</title>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/form.js"></script>
    <link href="${pageContext.request.contextPath}/layui/css/layui.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<form id="form" class="layui-form" action="${pageContext.request.contextPath}/updateUserServlet" method="post" onsubmit="return false">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名称</label>

        <div class="layui-input-inline">
            <input type="text" name="name" required  lay-verify="required" placeholder="请输入用户名" value="${user.name}" readonly style="width: 150px" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">QQ</label>
        <div class="layui-input-block">
            <input type="text" name="qq" required  lay-verify="required" placeholder="请输入QQ" value="${user.qq}" style="width: 250px" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="email" name="email" required  lay-verify="required" placeholder="请输入邮箱" value="${user.email}" class="layui-input" style="width: 250px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <c:if test="${user.sex=='男'}">
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked>
                <input type="radio" name="sex" value="女" title="女" >
            </div>
        </c:if>
        <c:if test="${user.sex=='女'}">
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked >
            </div>
        </c:if>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="number" name="age" required  lay-verify="required" placeholder="年龄"  value="${user.age}" style="width: 100px" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <textarea name="address" placeholder="请输入内容"  class="layui-textarea" style="width: 250px"><c:if test="${not empty user.address}">${user.address}
                </c:if></textarea>
        </div>
    </div>
    <input type="text" name="id" value="${user.id}" hidden>
    <input type="password" name="password" value="${user.password}" hidden>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" id="btn" class="layui-btn" lay-submit lay-filter="formDemo"  >修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['layer', 'form'], function(){
        var layer=layui.layer;
        var form = layui.form;
        var f = document.getElementById("form");
        //监听提交
        form.on('submit(formDemo)', function(){
            layer.confirm('确认修改用户？', {
                btn: ['确认','取消'] ,//按钮
            }, function(){
                f.submit();
            }, function(){
            });
        });
    });

</script>
</body>
</html>

