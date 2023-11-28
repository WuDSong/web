package Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
// 初学拦截器，/* 表示拦截所有数据
//@WebFilter("/*") //注解开发方式
public class Filter1 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter1的init（）被执行");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        System.out.println("拦截结果：");
        System.out.println("--------------------before");
        //对请求放行
        filterChain.doFilter(servletRequest,servletResponse);
        //放行后
        System.out.println("--------------------after");
    }

    public void destroy() {
        System.out.println("Filter1的destroy()被执行");
    }
}
