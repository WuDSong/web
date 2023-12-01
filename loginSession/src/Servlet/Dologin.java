package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Dologin", value = "/Dologin")
public class Dologin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//设置解读
        PrintWriter out = response.getWriter();

        //获取密码及用户名
        String uname = request.getParameter("username");
        String psw = request.getParameter("password");
        //是否保存cookie复选框
        String accept = request.getParameter("accept");
        System.out.println("登录信息：" + uname + "--" + psw + "--" + accept);
        //获取验证码
        String code1 = request.getParameter("checkname");
        HttpSession httpSession = request.getSession();
        String code2 = httpSession.getAttribute("checkcode").toString();
        //如果cookie有之前的登录信息则跳过登录
        //登录判定
        if ("admin".equals(uname) && "123".equals(psw)) {
            if (code2.equals(code1)) {
                request.setAttribute("uname", "admin");
                if (accept != null) {
                    System.out.println("创建cookie");
                    Cookie cookie = new Cookie("lastName", uname);
                    cookie.setMaxAge(60*60*24*10);//十天
                    response.addCookie(cookie);
                    System.out.println(cookie.getValue());
                }
                request.getRequestDispatcher("indexServlet").forward(request, response);
                System.out.println("----------------成功登录");
            } else {
                out.println("<h1>验证码错误</h1>");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } else {
            out.println("<h1>用户名或密码错误</h1>");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
