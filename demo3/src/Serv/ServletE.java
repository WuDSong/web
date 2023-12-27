package Serv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
//显示访问时间
@WebServlet(name = "ServletE", value = "/ServletE")
public class ServletE extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String lastTime=null;
        //读cookie
        Cookie[]cookies=request.getCookies();
        if(cookies.length!=0){
            for(Cookie ck:cookies){
                String cname=ck.getName();
                if ("LastTime".equals(cname)){
                    lastTime=ck.getValue();
                    break;
                }
            }
        }

        //cookie的优化版本
        //////////////////////////////////////////////////////////////////////////
        HttpSession httpSession=request.getSession();
        String uname=httpSession.getAttribute("name").toString();

        //////////////////////////////////////////////////////////////////////////

        if(lastTime!=null)
            out.println(uname+"上次时间"+lastTime);
        else out.println(uname+"首次");

        //获取上次访问时间 并 保存这次
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
        String time=sdf.format(new Date());
        Cookie cookie=new Cookie("LastTime",time);
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
