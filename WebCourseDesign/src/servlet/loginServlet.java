package servlet;

import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        //获得客户端参数
        String uname = request.getParameter("username");
        String psw = request.getParameter("password");
        UserBean userBean = new UserBean(uname, psw);
        Service service;

        service = new Service(DBUtils.getConnection());//初始化服务
        service.login(userBean);
        DBUtils.closeAll(service.connection, null, null);

        //用session实现页面直接的传输了
        HttpSession httpSession = request.getSession();
        //获取验证码
        String code1 = request.getParameter("code");
        String code2 = httpSession.getAttribute("checkcode").toString();
        System.out.println("输入的验证码："+code1+"生成的验证码："+code2);
        //是否保存cookie复选框
        String accept = request.getParameter("accept");
        if(!code2.equals(code1)){
            request.getRequestDispatcher("login.jsp").include(request, response);
            out.println("<script>let h1=document.querySelector('h1');h1.innerText='验证码错误';h1.style.color='red'</script>");
            return;
        }
        if (userBean.getId() > 0) {
            response.sendRedirect("index.jsp");
            //写数据到Session
            httpSession.setAttribute("user", userBean);
            if (accept != null) {
                httpSession.setAttribute("accept",1);
                System.out.println("2天内自动登录");
                //Session保存到cookie
                String sessionId=httpSession.getId();//获取Session标识符Id
                Cookie cookie = new Cookie("JSESSIONID", sessionId);
                cookie.setMaxAge(60*60*24*2);
                response.addCookie(cookie);
            }else httpSession.setAttribute("accept",0);

        } else {
            request.getRequestDispatcher("login.jsp").include(request, response);
            out.println("<script>let h1=document.querySelector('h1');h1.innerText='用户名或密码错误';h1.style.color='red'</script>");
            //request.getRequestDispatcher("login.jsp").include(request,response);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
