package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "indexServlet", value = "/indexServlet")
public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//设置解读
        PrintWriter out = response.getWriter();

        //检查之前的cookie
        String lastName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies.length != 0) {
            for (Cookie ck : cookies) {
                String cname = ck.getName();
                if ("lastName".equals(cname)) {
                    lastName = ck.getValue();
                    break;
                }
            }
        }
        if (lastName != null){
            System.out.println(lastName + "已经登录");
            response.sendRedirect("welcome.jsp");
        }
        else {
            out.println("<h1>indexServlet输出：您还未登录/没有选择十天自动登录</h1>");
            request.getRequestDispatcher("login.jsp").include(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
