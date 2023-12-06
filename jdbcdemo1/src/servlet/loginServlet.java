package servlet;

import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        request.setCharacterEncoding("utf-8");

        //获得客户端参数
        String uname= request.getParameter("username");
        String psw=request.getParameter("password");
        UserBean userBean=new UserBean(uname,psw);
        Service service;
        try {
            service=new Service(DBUtils.getConnection());//初始化服务
            service.login(userBean);
            service.connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //用session才能实现页面直接的传输了
        HttpSession httpSession=request.getSession();
        //写数据到Session
        httpSession.setAttribute("user",userBean);
        if(userBean.getId()>0){
            request.setAttribute("user",userBean);//放参数
            request.getRequestDispatcher("index.jsp").forward(request,response);
//            String url=request.getContextPath()+"/index.jsp?login='已登录'";
//            response.sendRedirect("index.jsp");

        }else {
            request.getRequestDispatcher("login.jsp").include(request,response);
            out.println("<script>let h1=document.querySelector('h1');h1.innerText='用户名或密码错误';h1.style.color='red'</script>");
            //request.getRequestDispatcher("login.jsp").include(request,response);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
