package servlet;

import po.Good;
import service.Service;
import util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "PutServlet", value = "/PutServlet")
public class PutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // 设置响应类型为JSON
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String gid = request.getParameter("gid");
        String number = request.getParameter("number");
        int id = -1, num = -1;
        int code = 0;//设置返回响应代码,约定0为失败，1为成功,2为错误
        String message = null; // 设置返回的消息
        try {
            id = Integer.parseInt(gid);
            num = Integer.parseInt(number);
        } catch (Exception e) {
            message = "异常：格式不对,错误信息" + e.getMessage();
            code = 2;
        }
        if (code != 2) {
            System.out.println("接受的axios的异步请求：将商品编号为" + gid + "的商品传入购物车");
            // 获取数据库当前物品信息
            Good good = new Service(DBUtils.getConnection()).getGoodById(id);
            //获得session ,放入
            HttpSession session = request.getSession();
            //约束 ： 购物车放置在Session中名字是cart
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute("cart");//从session拿存在购物车的物品
            if (cart == null) {
                System.out.println("---首次购物---");
                cart = new HashMap<Integer, Integer>();
                session.setAttribute("cart", cart);//写数据到Session
            }
            if (good == null) {//没有这个商品
                code = 0;
                message = "没有这个商品/已经下架";
            } else if (good.getNumber() == 0) {//数据库
                code = 0;
                message = "商品已经售完";
            } else {
                //商品放入购物车
                if (cart.containsKey(good.getId())) {
                    if (cart.get(good.getId()) + num < good.getNumber()) {
                        cart.put(good.getId(), cart.get(good.getId()) + num);//已经买过了,数量加num
                        code = 1;
                        message = "添加购物车成功";
                    } else {
                        code = 2;
                        message = "以达到购买该商品数量上限";
                    }

                } else {
                    cart.put(good.getId(), num);
                    code = 1;
                    message = "添加购物车成功";
                }
            }
            //int accept = (int) session.getAttribute("accept");
            //Session保存到cookie
//            String sessionId = session.getId();//获取Session标识符Id
//            System.out.println("放入购物车的Session id"+sessionId);
//            Cookie cookie = new Cookie("JSESSIONID", sessionId);
//            cookie.setMaxAge(60 * 60 * 24 * 2);
//            response.addCookie(cookie);

        }
        String jsonResponse = "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}"; // 构造JSON格式的响应数据
        out.print(jsonResponse); // 将响应数据写入响应流
        out.flush();
        out.close();
    }
}
