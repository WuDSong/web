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

        String code1=request.getParameter("checkname");
        HttpSession httpSession=request.getSession();
        String code2=httpSession.getAttribute("checkcode").toString();
        if(code2.equals(code1)){
            out.println("验证码正确!!!");
            out.println("登录ing");
        }else {
            out.println("验证码错误");
            request.getRequestDispatcher("index.jsp").include(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
