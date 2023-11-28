package Serv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet(name = "ServletF", value = "/ServletF")
public class ServletF extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//设置解读
        PrintWriter out = response.getWriter();
        HttpSession httpSession=request.getSession();
        String id=httpSession.getId();//获取Session标识符Id
        //Session保存到cookie
        Cookie cookie=new Cookie("JSESSIONID",id);
        cookie.setMaxAge(60*60*24);
        //写数据到Session
        httpSession.setAttribute("name","abc");
        if(httpSession.isNew()){
            out.println("新建的"+id);
        }else out.println("旧的"+id);
        //获取数据写在了ServletE
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
