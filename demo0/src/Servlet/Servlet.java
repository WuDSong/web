package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置生成的文档类型
        response.setContentType("text/html;charset=GB2312");
// 得到输出流
        PrintWriter out = response.getWriter();
        out.println("<HTML>");// 输出相应的HTML源文件
        out.println("<BODY>");
        out.println("<H2>我们正在学习Servlet编程</H2>");
        out.println("<H3>现在时间是: " + new Date() + "</H3>");// 输出系统时间
        out.println("</BODY>");
        out.println("</HTML>");
// 关闭输出流
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
