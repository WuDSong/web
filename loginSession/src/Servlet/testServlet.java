package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "testServlet", value = "/testServlet")
public class testServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String lastTime=null;
        String name=null;
        Cookie[]cookies=request.getCookies();
        if(cookies.length!=0){
            for(Cookie ck:cookies){
                String cname=ck.getName();
                if ("LastTime".equals(cname)){
                    lastTime=ck.getValue();
                    break;
                }
                if ("LastTime".equals(cname)){
                    lastTime=ck.getValue();
                    break;
                }
            }
        }
        if(lastTime!=null)
            out.println("上次时间"+lastTime);
        else out.println("首次");


        //获取上次访问时间 并 保存这次
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
        String time=sdf.format(new Date());
        Cookie cookie=new Cookie("LastTime","AMIN");
        cookie.setMaxAge(10);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
