package myServlet;

import myClass.Good;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "cartServlet", value = "/cartServlet")
public class cartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session从购物车里取出来
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>购物车</title>");
        out.println("<style> li {display: grid;grid-template-columns: 200px 50px 150px;overflow: auto;} </style>");
        out.println("</head>");
        out.println("<body>");
        HttpSession httpSession=request.getSession();
        HashMap<Good,Integer> cart=(HashMap<Good, Integer>) httpSession.getAttribute("cart");
        if(cart==null||cart.isEmpty()){
            out.println("<h2>对不起，您未购买任何东西</h2>");
        }else {
            out.println("<h1>您的选购的商品如下：</h1>");
            out.println("<ul>");
            float sumPrice=0;
            Iterator<Map.Entry<Good, Integer>> iterator = cart.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Good, Integer> entry = iterator.next();
                Good key = entry.getKey();
                Integer value = entry.getValue();
                // 处理键值对
                out.println("<li>");
                out.println("<span>"+key.getName()+"</span>");
                out.println("<span>×"+value+"</span>");
                float price=key.getPrice()*value;
                out.println("<span>￥"+price+"</span>");
                sumPrice+=price;
                out.println("</li>");
            }
            out.println("</ul>");
            out.println("总价：￥"+sumPrice);
        }
        out.println("<a href='ListGoodServlet' style='margin: 20px; font-weight: 700;'>返回继续购物</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
