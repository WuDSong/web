<%--
  Created by IntelliJ IDEA.
  User: Episode 33
  Date: 2023/12/5
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style>
        * {
            box-sizing: border-box;
        }
        h1 {
            text-align: center;
        }
        span {
            width: 80px;
            display: inline-block;
        }

        form {
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
<h1 class="title">注册</h1>
<form action="registerServlet" method="post">
    <hr>
    <span>用户名：</span><input type="text" name="username" placeholder="不能超过10个字符" autocomplete="off"><br>
    <span>密码：</span><input type="password" name="password" placeholder="不能超过10个字符" autocomplete="off"><br>
    <input type="submit" value="注册" class="btn">
    <a href="login.jsp" class="btn"><button type="button" value="去登录" class="btn">返回登录页面</button></a>
</form>
<script>
    let a
    function timeKeep(i) {
        a = setTimeout(function () { location.href = 'login.jsp' }, i)
        k = (i / 1000) - 1
        setInterval(function () { document.querySelector("h3 label").innerText = k; k-- }, 1000)
    }
</script>
</body>
</html>
