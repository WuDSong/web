<%@ page import="po.UserBean" %>
<%@ page import="po.Good" %><%--
  Created by IntelliJ IDEA.
  User: Episode 33
  Date: 2023/12/21
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= request.getAttribute("gname")%></title>
    <link rel="stylesheet" href="./css/font_69jvncwbzfe/iconfont.css">
    <link rel="stylesheet" href="./css/DetailGoodPage.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>

<%
    Good good= (Good) request.getAttribute("currentGood");
    HttpSession httpSession=request.getSession();
    UserBean userBean= (UserBean) httpSession.getAttribute("user");
    if (userBean != null && userBean.getId() > 0 && good!=null){
%>
<form class="box" action="#">
    <img src="./img/<%= good.getPicture()%>" alt="" width="600px">
    <div style="margin-left:50px ;padding: 5px;position: relative;">
        <div class="gName"><%= good.getName()%></div>
        <div class="gPrice">￥<%= good.getPrice()%></div>
        <div class="else">库存：<%= good.getNumber()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 所在地：<%= good.getCity()%></div>
        <div style="margin-top: 10px;">
            <span>购买数量&nbsp;</span>
            <button class="btn minus" type="button">-</button>
            <input type="text" class="num" placeholder="0">
            <button class="btn plus" type="button">+</button>
        </div>
        <button class="addToCart" type="button">
            <div class="iconfont icon-gouwuchetianjia" style="font-size: 20px;">&nbsp;放入购物车</div>
        </button>
<%--        <button class="addToOrder" type="button">--%>
<%--            <div style="font-size: 20px;">&nbsp;立即购买</div>--%>
<%--        </button>--%>
<%--        <button class="test" type="button">123123</button>--%>
    </div>
</form>
<script src="./js/DetailGoodPage.js"></script>
<script>
    document.querySelector(".addToCart").addEventListener("click", () => {
        if (num == 0){
            alert("购买数量不能为零")
        }
        else {
            let data = new URLSearchParams();
            data.append('gid', '<%= good.getId()%>');
            data.append('number',num)
            axios({
                url: 'http://localhost:8080/WCD/PutServlet',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            }).then(result => {
                console.log('添加到购物车的结果：',result.data);
                let code=result.data.code
                let message=result.data.message
                if(code==1||code==2){
                    num=0
                    ChangeNum(0)
                }
                alert(message)
            }).catch(error => {
                console.log('出错误：');
                console.log(error);
            })
        }
    })
    document.querySelector(".addToOrder").addEventListener("click", () => {
        if (num == 0){
            alert("购买数量不能为零")
        }
        else {
            let data = new URLSearchParams();
            data.append('gid', '<%= good.getId()%>');
            data.append('number',num)
            axios({
                url: 'http://localhost:8080/WCD/submitOrderServlet',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            }).then(result => {
                console.log('添加到购物车的结果：',result.data);
                let code=result.data.code
                let message=result.data.message
                if(code==1||code==2){
                    num=0
                    ChangeNum(0)
                }
                alert(message)
            }).catch(error => {
                console.log('出错误：');
                console.log(error);
            })
        }
    })
</script>
<% } %>
</body>
</html>
