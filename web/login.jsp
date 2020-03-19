<%--
  Created by IntelliJ IDEA.
  User: 陌意随影
  Date: 2020/1/24 0024
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <style>
        #p{
            margin-bottom: 20px;
            text-align: center;
            font-size: 20px;
            font-family: 微软雅黑;
            color: black;
        }
        .username{
            border-bottom-left-radius: 3px;
            border-bottom-right-radius: 3px;
            border-top-left-radius: 3px;
            border-top-right-radius: 3px;

            border-radius: 3px;
            border-color: aqua;
            width:150px;
            height: 30px;
        }
        #div{
            margin: 0 auto;
            margin-left: 33%;
            width:400px;
            height:100%; border:1px
        }
    </style>
    <script>
   function reflushcode() {
           var img = document.getElementById("checkcode");
           img.src="${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
   }
   window.onload=function () {
       var img = document.getElementById("checkcode");
       img.src="${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
   }
    </script>
    <title>登录页面</title>
</head>
<body>
<p id="p">管理员登录</p><br>


<div id="div">
    <form  action="${pageContext.request.contextPath}/userLoginServlet" method="post">
        <table>
            <tr>
                <td> 用户名：</td>
                <td > <input type="text" class="username" name="username" placeholder="请输入用户名" checked value="${sessionScope.user.name}"></td>
                <td style="color: red">${requestScope.login_err}</td>
            </tr>
            <tr>
                <td> 密&nbsp码：</td>
                <td > <input type="password" class="username" name="password" placeholder="请输入密码" value="${sessionScope.user.password}"></td>
            </tr>
            <tr>
            <td> 验证码：</td>
            <td> <input type="text" class="username" name="checkcode" placeholder="请输入验证码" required="required"></td>
           </tr>
            <tr>
                <td width="50px"> <a href="javascript:reflushcode()" >看不清？刷新一下</a></td>
                <td > <img href="${pageContext.request.contextPath}/checkCodeServlet" id="checkcode" name="checkcode" alt="验证码" onclick="reflushcode()"></td>
                <td style="color: red">${requestScope.checkcode_err}</td>
            </tr>
            <tr>
                <td colspan="2" > <input type="submit"   value="登录" style="width: 80px ;height: 30px;margin-left: 33%"></td>
            </tr>

        </table>
    </form>
</div>

</body>
</html>