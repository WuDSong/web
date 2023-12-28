package servlet;

import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "logoutServlet", value = "/logoutServlet")
public class logoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("**开始删除用户信息");
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        HttpSession httpSession = request.getSession();
        UserBean userBeant = (UserBean) httpSession.getAttribute("user");
        if (userBeant != null) {
            Service service;
            service = new Service(DBUtils.getConnection());//初始化服务
            service.logout(userBeant);//执行服务层 数据库对用户注销
            DBUtils.closeAll(service.connection, null, null);
            System.out.println("--删除cookie");
            httpSession.setAttribute("user", null);//删除cookie的用户信息
            request.setAttribute("user", null);//删除请求用户的信息
            httpSession.setAttribute("cart",null);//删除购物车信息
        }
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
