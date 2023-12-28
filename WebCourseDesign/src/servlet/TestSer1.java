package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TSer1", value = "/TSer1")
public class TestSer1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //System.out.println("接受的axios的异步请求："+data);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // 设置响应类型为JSON
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String data = request.getParameter("username");
        System.out.println("接受的axios的异步请求："+data);
        // 执行数据库处理
        // ...

        // 返回响应,约定0为失败，1为成功
        int code=0;
        String message = "处理成功"; // 设置返回的消息
        String jsonResponse = "{\"code\":\""+code+"\",\"message\":\"" + message + "\"}"; // 构造JSON格式的响应数据
        out.print(jsonResponse); // 将响应数据写入响应流
        out.flush();
        out.close();

//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            String username = request.getParameter("username");
//            String message = "处理成功";
//            if (username == null || username.isEmpty()) {
//                message = "请输入用户名";
//            }
//            out.println("{ \"message\": \"" + message + "\" }");
//        } catch (Exception e) {
//            e.printStackTrace();
//            out.println("{ \"message\": \"发生错误\", \"error\": \"" + e.getMessage() + "\" }");
//        } finally {
//            out.close();
//        }
    }
}
