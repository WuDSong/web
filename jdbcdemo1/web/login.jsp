<%--
  Created by IntelliJ IDEA.
  User: Episode 33
  Date: 2023/12/5
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        *{
            box-sizing: border-box;
        }
        h1 {
            text-align: center;
        }
        span {
            width: 80px;
            display: inline-block;
        }
        form{
            margin-top: 30px;
            text-align: center;
        }
        .btn {
            margin: 10px;
            width: 100px;
            height: 30px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<h1>登录</h1>
<form action="loginServlet" method="post">
    <hr>
    <span>用户名：</span><input type="text" name="username" placeholder="不能超过10个字符" autocomplete="off"><br>
    <span>密码：</span><input type="password" name="password" placeholder="不能超过10个字符" autocomplete="off"><br>
    <input type="submit" value="登录" class="btn">
    <a href="registration.jsp" class="btn"><button type="button" value="注册" class="btn">注册</button></a>
</form>
</body>
</html>
