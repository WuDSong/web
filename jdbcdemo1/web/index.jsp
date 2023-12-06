<%@ page import="po.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Episode 33
  Date: 2023/12/5
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<% UserBean userBean = (UserBean) request.getAttribute("user"); %>
<% if (userBean != null && userBean.getId() > 0) { %>
<h1>Welcome!</h1>
<%--<a href="logoutServlet" class="btn"><button type="button" value="去注销" class="btn">退出账户</button></a>--%>
<a href="logoutServlet" class="btn"><button type="button" value="去注销" class="btn">注销账户</button></a>
<hr>
<img src="https://ts1.cn.mm.bing.net/th/id/R-C.2ea4c5b998603f8c2063c80f8f9928b5?rik=3Ks8xULgcFIU2Q&riu=http%3a%2f%2fimg.xintp.com%2f2019%2f12%2f10%2fta0ybcui0lh.jpg&ehk=fBDxTTIr1rLMFa4DcH%2f2db4qQTMEAvmS4GL%2faUZwDd0%3d&risl=&pid=ImgRaw&r=0" height="600px">
<% } else { %>
<h1>Please log in to view the current page</h1>
<a href="login.jsp" class="btn"><button type="button" value="去登录" class="btn">登录</button></a>
<% } %>
</body>
<%--为什么不采用给btu加监听addEvent... ajax/xhr？  2023/12/6--%>
<%--答：花了3小时，加了没什么用主要是。ajax,xhr回应的响应报文顶多就是html文本，你还要用js改整个document.
    其次，怎么返回对应的响应报文也是麻烦，logoutServlet 那里你要怎么写，你直接那里跳转，那这里index怎么办？
    还有就是。。。

    解决：采用<a>标签直接转servlet就行了，登录:request.getRequestDispatcher("index.jsp").forward(request,response);
    注销：response.sendRedirect("index.jsp");//成功--%>
</html>
