package servlet;

import po.Good;
import service.Service;
import util.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DetailGoodServlet", value = "/DetailGoodServlet")
public class DetailGoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        request.setCharacterEncoding("utf-8");
        String id=request.getParameter("id");
        System.out.println(request.getContextPath());
        Good good=new Service(DBUtils.getConnection()).getGoodById(Integer.parseInt(id));//通过用户点击的物品id查找对应的物品，并加载到页面上
        request.setAttribute("currentGood",good);
        request.setAttribute("gname",good.getName());
        request.getRequestDispatcher("DetailGoodPage.jsp").include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

//out.println("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>"+good.getName()+"</title>\n" +
//                "    <link rel=\"stylesheet\" href=\"./download/font_69jvncwbzfe/iconfont.css\">\n" +
//                "    <link rel=\"stylesheet\" href=\"./DetailGood.css\">\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "    <form class=\"box\" action=\"#\">\n" +
//                "        <img src=\"./img/"+good.getPicture()+"\" alt=\"\" width=\"600px\">\n" +
//                "        <div style=\"margin-left:50px ;padding: 5px;position: relative;\">\n" +
//                "            <div class=\"gName\">"+good.getPrice()+"</div>\n" +
//                "            <div class=\"gPrice\">￥"+good.getPrice()+"</div>\n" +
//                "            <div class=\"else\">库存："+good.getNumber()+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 所在地:"+good.getCity()+"</div>\n" +
//                "            <div style=\"margin-top: 10px;\">\n" +
//                "                <span>购买数量&nbsp;</span>\n" +
//                "                <button class=\"btn minus\" type=\"button\">-</button>\n" +
//                "                <input type=\"text\" class=\"num\" placeholder=\"0\">\n" +
//                "                <button class=\"btn plus\" type=\"button\">+</button>\n" +
//                "            </div>\n" +
//                "            <button class=\"addToCart\" type=\"submit\">\n" +
//                "                <div class=\"iconfont icon-gouwuchetianjia\" style=\"font-size: 20px;\">&nbsp;放入购物车</div>\n" +
//                "            </button>\n" +
//                "        </div>\n" +
//                "    </form>\n" +
//                "    <script src=\"./DetailGood.js\"></script>\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>");