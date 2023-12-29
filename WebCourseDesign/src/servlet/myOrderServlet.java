package servlet;

import dao.Dao;
import po.Good;
import po.Order;
import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "myOrderServlet", value = "/myOrderServlet")
public class myOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Service service=new Service(DBUtils.getConnection());
        UserBean userBean= (UserBean) request.getSession().getAttribute("user");
        if(userBean.getId()<=0) return;
        List<Order> orders=service.getMyOrder(userBean.getId());
        out.println("<html>");
        out.println("<head>");
        out.println("<title>我的订单</title>");
        out.println("<style> li {display: grid;grid-template-columns: 110px 300px 150px 150px 150px 200px;overflow: auto;} </style>");
        out.println("</head>");
        out.println("<body><ul>");
        float sumPrice=0;
        out.println("<li><span>物品</span><span>名字</span><span>单价</span><span>数量</span><span>小计</span><span>交易时间</span></li>");
        out.println("<hr>");
        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder=orders.get(i);
            Good tempGood=service.getGoodById(currentOrder.getGid());
            out.println("<li style=\"margin-bottom:4px ;\">");
            out.println("<img src=\"./img/"+tempGood.getPicture()+"\" alt=\"\" width=\"100px\">");
            out.println("<span>"+tempGood.getName()+"</span>");
            out.println("<span>"+tempGood.getPrice()+"</span>");
            out.println("<span>×"+currentOrder.getGnum()+"</span>");
            float price=tempGood.getPrice()* currentOrder.getGnum();
            out.println("<span>￥"+price+"</span>");
            out.println("<span>"+currentOrder.getOrderTime()+"</span>");
            sumPrice+=price;
            out.println("</li>");
        }
        out.println("<ul></body>");
        out.println("<hr>");
        out.println("总价：￥"+sumPrice);
        out.println("</html>");
        out.close();
        DBUtils.closeAll(service.connection,null,null);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
