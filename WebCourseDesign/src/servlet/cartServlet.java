package servlet;

import po.Good;
import po.UserBean;
import service.Service;
import util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
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
        out.println("<style> li {display: grid;grid-template-columns: 400px 150px 150px 100px;overflow: auto;} </style>");
        out.println("</head>");
        out.println("<body>");

        HttpSession httpSession=request.getSession();
        UserBean userBean= (UserBean) httpSession.getAttribute("user");
        System.out.println(userBean.getId());
        if(userBean == null || userBean.getId() < 0)
            return;
        HashMap<Integer,Integer> cart=(HashMap<Integer, Integer>) httpSession.getAttribute("cart");
        if(cart==null||cart.isEmpty()){
            out.println("<h2>对不起，您未购买任何东西</h2>");
        }else {
            out.println("<h1>您的选购的商品如下：</h1>");
            out.println("<ul>");
            float sumPrice=0;
            Service service=new Service(DBUtils.getConnection());
            Iterator<Map.Entry<Integer, Integer>> iterator = cart.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                Good good=service.getGoodById(key);
                // 处理键值对
                out.println("<li>");
                out.println("<span>"+good.getName()+"</span>");
                out.println("<span>×"+value+"</span>");
                float price=good.getPrice()*value;
                out.println("<span>￥"+price+"</span>");
                out.println("<button type=\"button\" value=\""+good.getId()+"\">删除</button>");
                sumPrice+=price;
                out.println("</li>");
            }

            DBUtils.closeAll(service.connection,null,null);
            out.println("</ul>");
            out.println("总价：￥"+sumPrice);
        }
        out.println("<a href='index.jsp' style='margin: 20px; font-weight: 700;'>返回继续购物</a>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js\"></script>");
        out.println("<script>const buttons = document.querySelectorAll('button');\n" +
                "        buttons.forEach(button => {\n" +
                "            button.addEventListener('click', () => {\n" +
                "            console.log(button.value);\n" +
                "            let data = new URLSearchParams();\n" +
                "            let num=prompt(\"需要删除的数量\", \"\");\n" +
                "            if(num!=null&&num!=\"\"){\n" +
                "            data.append('gid', button.value);\n" +
                "            data.append('number',num)\n" +
                "            axios({\n" +
                "                url: 'http://localhost:8080/WCD/RemoveServlet',\n" +
                "                method: 'POST',\n" +
                "                headers: {\n" +
                "                    'Content-Type': 'application/x-www-form-urlencoded'\n" +
                "                },\n" +
                "                data: data\n" +
                "            }).then(result => {\n" +
                "                console.log('删除购物车的结果:',result.data);\n" +
                "                let code=result.data.code\n" +
                "                let message=result.data.message\n" +
                "                alert(message)\n" +
                "                location.reload(true);\n" +
                "            }).catch(error => {\n" +
                "                console.log('出错误：');\n" +
                "                console.log(error);\n" +
                "            })" +
                "            };\n" +
                "        })});" +
                "</script>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
