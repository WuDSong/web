import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Servlet4", value = "/Servlet4")
public class Servlet4 extends HttpServlet {
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
        if(lastTime!=null)
            out.println("上次时间"+lastTime);
        else out.println("首次");

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
