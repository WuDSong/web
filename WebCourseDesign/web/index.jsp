<%@ page import="po.UserBean" %>
<%@ page import="po.Good" %>
<%@ page import="java.util.List" %>
<%@ page import="service.Service" %>
<%@ page import="util.DBUtils" %>
<%@ page import="java.io.PrintWriter" %><%--
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
    <link rel="stylesheet" href="./css/index.css">
</head>
<body>
<%  HttpSession httpSession=request.getSession();
    UserBean userBean = (UserBean) httpSession.getAttribute("user");

 if (userBean != null && userBean.getId() > 0) { %>
<div class="popup" id="welcomePopup">
    <span class="sign"></span>
    Welcome!
</div>
<script>
    window.onload = function () {
        var popup = document.getElementById("welcomePopup");
        setTimeout(function () {
            popup.style.opacity = 0;
        }, 2000);
    };
</script>
<div style="text-align: right;">
    <span><%=userBean.getUname()%>></span>
    <a href="cartServlet" class="btn"><button type="button" value="查看我的购物车" class="btn">我的购物车</button></a>
    <a href="myOrderServlet" class="btn"><button type="button" value="我的订单" class="btn">我的订单</button></a>
    <a href="exitServlet" class="btn"><button type="button" value="退出" class="btn">退出账户</button></a>
    <a href="logoutServlet" class="btn"><button type="button" value="去注销" class="btn">注销账户</button></a>
</div>
<hr>
<% String url = request.getContextPath() + "/DetailGoodServlet?id=";//用于传递参数
    List<Good> goods = new Service(DBUtils.getConnection()).getGoods();%>

<div class="box">
    <%for (int i = 0; i < goods.size(); i++) {%>
    <a href="<%= url+goods.get(i).getId()+"&&gname="+goods.get(i).getName() %>" target="_blank">
        <div class="good">
            <img src="./img/<%= goods.get(i).getPicture()%>" alt="图片" width="200px">
            <div class="gName"><%= goods.get(i).getName()%></div>
            <div class="gPrice">￥<%=goods.get(i).getPrice()%></div>
        </div>
    </a>
    <% } %>
</div>
<% } else { %>
<h1>Please log in to view the current page</h1>
<a href="login.jsp">
    <button type="button" value="去登录" class="btnLogin">登录</button>
</a>
<script>
    document.body.style.backgroundSize = 'cover'
    document.body.style.backgroundImage = "url('./img/index.png')";
</script>
<% }
%>
</body>
<%--为什么不采用给btu加监听addEvent... ajax/xhr？  2023/12/6--%>
<%--答：花了3小时，加了没什么用主要是。ajax,xhr回应的响应报文顶多就是html文本，你还要用js改整个document.
    其次，怎么返回对应的响应报文也是麻烦，logoutServlet 那里你要怎么写，你直接那里跳转，那这里index怎么办？
    还有就是。。。

    解决：采用<a>标签直接转servlet就行了，登录:request.getRequestDispatcher("index.jsp").forward(request,response);
    注销：response.sendRedirect("index.jsp");//成功--%>
</html>