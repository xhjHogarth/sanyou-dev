package com.sanyou.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: asus
 * Date: 2021/6/8
 * Time: 17:17
 * Version:V1.0
 */
public class UserGroupFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UserGroupFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
