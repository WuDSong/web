package servlet;

import po.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "RemoveServlet", value = "/RemoveServlet")
public class RemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // 设置响应类型为JSON
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession httpSession = request.getSession();
        UserBean userBean = (UserBean) httpSession.getAttribute("user");
        System.out.println(userBean.getId());
        if (userBean == null || userBean.getId() < 0)
            return;
        HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) httpSession.getAttribute("cart");
        int code = 0;//设置返回响应代码,约定0为失败，1为成功,2为错误
        String message = null; // 设置返回的消息
        int id = -1;
        int num = -1;
        String gid = request.getParameter("gid");
        String number = request.getParameter("number");
        System.out.println("RemoveServlet收的axios的异步请求：将购物车商品编号为" + gid + "的商品删除" + num + "个");
        try {
            id = Integer.parseInt(gid);
            num = Integer.parseInt(number);
        } catch (Exception e) {
            code = 2;
            message = "异常：发送信息格式不对,错误信息" + e.getMessage();
        }
        if (code != 2) {
            if (num < 0) {
                message = "不能是负数哦~";
                code = 2;
            } else if (cart.containsKey(id)) {
                int temp = cart.get(id) - num;
                if (temp > 0) {
                    cart.put(id, temp);
                    message = "删除购物车成功";
                    code = 1;
                } else if (temp == 0) {
                    cart.remove(id);
                    message = "删除购物车成功";
                    code = 1;
                } else {
                    message = "您没有订购这么多";
                    code = 0;
                }
            } else message = "删除购物车失败";
        } else {
            message = "数据异常：购物车无该商品";
            code = 0;
        }

        httpSession.setAttribute("cart", cart);
        String jsonResponse = "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}"; // 构造JSON格式的响应数据
        out.print(jsonResponse); // 将响应数据写入响应流
        out.flush();
        out.close();
    }
}
