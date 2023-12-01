package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        request.setCharacterEncoding("utf-8");
        //获得客户端参数
        String uname= request.getParameter("username");
        String psw=request.getParameter("password");
        if("admin".equals(uname)&&"123".equals(psw)){
            request.setAttribute("uname","admin");
            request.getRequestDispatcher("Servlet2").forward(request,response);
        }else {
            out.println("<h1>用户名或密码错误</h1>");
            request.getRequestDispatcher("login.html").include(request,response);
            //request.getRequestDispatcher("login.jsp").include(request,response);
        }
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
