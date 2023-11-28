package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
//模仿老师的Servlet2和ServletB
@WebServlet(name = "Servlet2", value = "/Servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
//        out.println("这里是Servlet2/B<br>");
        //取request数据
        String str=request.getAttribute("uname").toString();
        System.out.println(str);
        out.println("Welcome! Mr."+str);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
