package myServlet;

import myClass.Good;
import myClass.GoodDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//1.把用户购买的商品放置在Session中
//2.将页面重定向到用户自己购买的商品列表中
@WebServlet(name = "PutServlet", value = "/PutServlet")
public class PutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.知道用户选择的商品,getId
        String gid=request.getParameter("id");
        System.out.println("选择的商品id:"+gid);
        if(gid==null){
            response.sendRedirect("ListGoodServlet");
        }
        //根据id拿商品
        Good good= GoodDB.getById(gid);
        //获得session ,放入
        HttpSession session=request.getSession();
        //约束 ： 购物车放置在Session中名字是cart
        HashMap<Good,Integer> cart=(HashMap<Good, Integer>) session.getAttribute("cart");//从session拿存在购物车的物品
        if(cart==null){
            System.out.println("---首次购物---");
            cart=new HashMap<Good,Integer>();
            session.setAttribute("cart",cart);//写数据到Session
        }
        //商品放入购物车
        if(cart.containsKey(good)){
            cart.put(good,cart.get(good)+1);//已经买过了,数量加一
        }else cart.put(good,1);

        //Session保存到cookie
        String id=session.getId();//获取Session标识符Id
        Cookie cookie=new Cookie("JSESSIONID",id);
        cookie.setMaxAge(60*10);
        if(session.isNew()){
            System.out.println("新建的session"+id);
        }else System.out.println("旧的session"+id);

        response.addCookie(cookie);
        //重定向购物车页面
        response.sendRedirect("cartServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
