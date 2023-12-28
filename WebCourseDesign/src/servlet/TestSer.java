package servlet;

import po.Good;
import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TSer", value = "/TSer")
public class TestSer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        request.setCharacterEncoding("utf-8");

        UserBean userBean=new UserBean(99,"wda","wwwww");//测试
        request.setAttribute("user",userBean);//放参数
        request.getRequestDispatcher("index.jsp").include(request,response);

        out.println("TSer的测试");
        String url=request.getContextPath()+"/DetailGoodServlet?id=";//用于传递参数
        System.out.println("url"+url);
        List<Good> goods=new Service(DBUtils.getConnection()).getGoods();
        out.println("<div class=\"box\">");
        for (int i = 0; i < goods.size(); i++) {
            out.println("<a href=\""+url+goods.get(i).getId()+"\"><div class=\"good\">\n" +
                    "            <img src=\"./img/"+goods.get(i).getPicture()+"\" alt=\"\" width=\"200px\">\n" +
                    "            <div class=\"gName\">"+goods.get(i).getName()+"</div>\n" +
                    "            <div class=\"gPrice\">￥"+goods.get(i).getPrice()+"</div>\n" +
                    "        </div></a>");
        }
        out.println("</div>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
