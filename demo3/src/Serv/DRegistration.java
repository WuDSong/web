package Serv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DRegistration", value = "/DRegistration")
public class DRegistration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //解决post请求的中文乱码
        request.setCharacterEncoding("utf-8");
        //获得客户端参数
        String[] sex = request.getParameterValues("sex"); //性别
        String []fos=request.getParameterValues("favorite");//爱好
        String degree=request.getParameter("学历");//学历
        String introduce=request.getParameter("introduce");//介绍
        String accept=request.getParameter("accept");//协议
        //获取复选框的值
        out.print("性别：");
        for(String item:sex){
            out.println(item);
        }
        out.print("<br>爱好：<ul>");
        for(String item:fos){
            out.println("<li>"+item+"</li>");
        }
        out.print("</ul>");
        out.print("学历： "+degree+"<hr>");
        out.print("自我介绍："+introduce+"<hr>是否接受许可协议:");
        System.out.println(accept);
        if (accept.equals("on"))
            out.print("是");
        else
            out.print("否");

        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
