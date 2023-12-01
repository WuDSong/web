package Serv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletLog", value = "/ServletLog")
public class ServletLog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//设置解读
        PrintWriter out = response.getWriter();
        //密码及用户名
        String uname= request.getParameter("username");
        String psw=request.getParameter("password");

        if("admin".equals(uname)&&"123".equals(psw)){
            request.setAttribute("uname","admin");
            request.getRequestDispatcher("Servlet2").forward(request,response);
        }else {
            out.println("<h1>用户名或密码错误</h1>");
            request.getRequestDispatcher("index.jsp").include(request,response);
            //request.getRequestDispatcher("login.jsp").include(request,response);
        }
        ////////////////////////////////////////////////////////////////////////////////////////

        //验证码
        String code1=request.getParameter("checkname");
        HttpSession httpSession=request.getSession();
        String code2=httpSession.getAttribute("checkcode").toString();
        if(code2.equals(code1)){
            out.println("验证码正确!!!");
            out.println("登录ing");
        }else {
            out.println("<h1>验证码错误</h1>");
            request.getRequestDispatcher("index.jsp").include(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
