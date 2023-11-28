package Serv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletD", value = "/ServletD")
public class ServletD extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //读cookie
        Cookie []cookies=request.getCookies();
        //遍历
        if(cookies.length!=0){
            for(Cookie ck:cookies){
                System.out.println(ck.getName()+"====="+ck.getValue());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
