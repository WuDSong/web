package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter3",value = "/*") //注解开发方式
public class FilterLogin implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FilterLogin开始拦截---所有数据---");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        servletRequest.setCharacterEncoding("utf-8");
//        servletResponse.setContentType("text/html;charset=utf-8");

        //请求路径
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        String path=req.getContextPath();
        System.out.println("--->我的servletRequest路径："+path);//获取请求路径

        //如果不是/user
        if(!path.startsWith("/user")){
            System.out.println("拦截！！！");
            filterChain.doFilter(servletRequest,servletResponse);//放行
            return;
        }

        //判断是否为空
        HttpSession session=req.getSession();
        String user=(String) session.getAttribute("uname");
        if(user!=null){
            filterChain.doFilter(servletRequest,servletResponse);//放行
        }else {
            ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");
        }
    }

    public void destroy() {

    }
}