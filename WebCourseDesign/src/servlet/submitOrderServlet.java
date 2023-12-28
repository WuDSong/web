package servlet;

import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "submitOrderServlet", value = "/submitOrderServlet")
public class submitOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean= (UserBean) session.getAttribute("user");
        HashMap<Integer,Integer> cart=(HashMap<Integer, Integer>) session.getAttribute("cart");
        //uid,gid,gnum
        if(new Service(DBUtils.getConnection()).submitAllGood(userBean.getId(),cart)){
            System.out.println("下单成功");
            session.removeAttribute("cart");
            response.sendRedirect("http://localhost:8080/WCD/cartServlet");
            PrintWriter out = response.getWriter();
            out.println("123");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
