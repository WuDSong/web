package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

//request作用域：拥有存储数据的空间，作用范围是一次请求有效(一次请求可以经过多次转发)
//        。可以将数据存入request后，在一次请求过程中的任何位置进行获取
//        。可传递任何数据(基本数据类型、对象、数组、集合等)
//        •：request.setAttribute(key,value);
//        。以键值对形式存储在request作用域中。key为String类型，value为Object类型•取数据：request.getAttribute(key);
//        。通过String类型的key访问Object类型的value

@WebServlet(name = "ServletA", value = "/ServletA")
public class ServletA extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        out.println("这里是ServletA");

        //存数据在 request 中
        request.setAttribute("uname","WuDongSong");
        //获取请求分派对象
        RequestDispatcher rd=request.getRequestDispatcher("Servlet2");
        //请求转给Servlet2处理，但是浏览器路径不变，仍然是http://localhost:8080/demo2/ServletA
        rd.forward(request,response);

        //重定向,模仿两次请求，将上面获取请求分派对象代码注释后使用,将无法输出uname的值，且浏览器路径
        //变成http://localhost:8080/demo2/Servlet2 将处理的数据Servlet2暴露
        //response.sendRedirect("Servlet2");

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
