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

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            service.registration(userBean);
            service.connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(userBean.getId()>0){
            out.println("<h1>注册成功</h1><h3><label style='color:cornflowerblue;'>5</label>&nbsp;秒后跳转到登录页面...</h3><button class='cancel'>点击取消</button>");
            request.getRequestDispatcher("registration.jsp").include(request,response);
            out.println("<script>");
            out.println("timeKeep(5000)");
            out.println("let cancel = document.querySelector('.cancel')");
            out.println("cancel.addEventListener('click', () => {");
            out.println("a = clearTimeout(a)");
            out.println("if (a == undefined){");
            out.println("document.body.removeChild(document.querySelector('h3'))");
            out.println("document.body.removeChild(cancel)}})");
            out.println("document.querySelector('.title').style.display = 'none'");
            out.println("</script>");
        }else{
            out.println("<h1 style='color:red'>注册失败</h1>");
            request.getRequestDispatcher("registration.jsp").include(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
