package com.cmcc.ict.exam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();

        // 从session里取登录信息
        String ecid = (String)session.getAttribute("ecid");
        String appid = (String)session.getAttribute("appid");
        
        // 登陆页面无需过滤
        if(path.indexOf("/login_new.jsp") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        //如果是查看成绩详情页面无需过滤登录
        if(path.indexOf("/exam/admin/resultDetail.action") > -1){
        	chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (ecid == null || "".equals(ecid) || appid == null || "".equals(appid)) {
            // 跳转到登陆页面
            servletResponse.sendRedirect("/exam/login_new.jsp");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}