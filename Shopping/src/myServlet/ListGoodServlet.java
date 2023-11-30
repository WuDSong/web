package myServlet;
import myClass.Good;
import myClass.GoodDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "ListGoodServlet", value = "/ListGoodServlet")
public class ListGoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");// 设置生成的文档类型
        PrintWriter out = response.getWriter();// 得到输出字符输出流
        Collection<Good> goods= GoodDB.getAll();//获得所有商品
        out.println("<html>");
        out.println("<head>");
        out.println("<title>购物-ing</title>");
        out.println("<style> li {display: grid;grid-template-columns: 200px 200px 150px;overflow: auto;} </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>今日热销商品如下：</h1>");
        String path=request.getContextPath()+"/cartServlet";
        out.println("<a href='"+path+"' style='float: right ;margin: 20px; font-weight: 700; color: #000;' >我的购物车</a>");
        out.println("<ul>");
        for(Good good:goods){
            //用户点击超链接，获取其id，用get
            String url=request.getContextPath()+"/PutServlet?id="+good.getId();
            out.println("<li>");
            out.println("<span>"+good.getName()+"</span>");
            out.println("<span>￥"+good.getPrice()+"</span>");
            out.println("<span><a href='"+url+"'>点击购买</a></span>");
            out.println("</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
