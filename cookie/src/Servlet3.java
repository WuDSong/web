import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Servlet3", value = "/Servlet3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true); // 获取Session，如果不存在则创建一个新的Session
        response.setContentType("text/html;charset=utf-8");//设置解读
        // 获取上次访问时间
        Date lastVisit = (Date) session.getAttribute("lastVisit");

        // 保存当前时间到Session中
        Date currentVisit = new Date();
        session.setAttribute("lastVisit", currentVisit);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (lastVisit != null) {
            out.println("上次访问时间：" + lastVisit);
        } else {
            out.println("这是您第一次访问本网站。");
        }
        out.println("</body></html>");
        String sessionId = session.getId();//获取Session标识符Id
        System.out.println("放入购物车的Session id"+sessionId);
        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        cookie.setMaxAge(60 * 60 * 24 * 2);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
