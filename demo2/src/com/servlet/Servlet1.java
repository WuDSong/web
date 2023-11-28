package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet1", value = "/Servlet1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out =response.getWriter();
        out.println("这里是Servlet1");

        //获取请求分配对象  参数URL:
        RequestDispatcher rd=request.getRequestDispatcher("Servlet2");
        rd.include(request,response);
        out.println("22222222222222222");
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
