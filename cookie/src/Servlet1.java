import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet1", value = "/Servlet1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//设置解读
        PrintWriter out = response.getWriter();

        HttpSession httpSession=request.getSession();
        String id=httpSession.getId();//获取Session标识符Id
        httpSession.setMaxInactiveInterval(60*60*24);

        //Session保存到cookie
        Cookie cookie=new Cookie("JSESSIONID",id);
        cookie.setMaxAge(60*60*24);
        out.println("保存成功");
        //写数据到Session
        httpSession.setAttribute("name","wds");
        if(httpSession.isNew()){
            out.println("新建的"+id);
        }else out.println("旧的"+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
