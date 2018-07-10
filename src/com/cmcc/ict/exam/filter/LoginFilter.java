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
        // ��������������Ҫ�õ�request,response,session����
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        // ����û������URI
        String path = servletRequest.getRequestURI();

        // ��session��ȡ��¼��Ϣ
        String ecid = (String)session.getAttribute("ecid");
        String appid = (String)session.getAttribute("appid");
        
        // ��½ҳ���������
        if(path.indexOf("/login_new.jsp") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        //����ǲ鿴�ɼ�����ҳ��������˵�¼
        if(path.indexOf("/exam/admin/resultDetail.action") > -1){
        	chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // �ж����û��ȡ��Ա����Ϣ,����ת����½ҳ��
        if (ecid == null || "".equals(ecid) || appid == null || "".equals(appid)) {
            // ��ת����½ҳ��
            servletResponse.sendRedirect("/exam/login_new.jsp");
        } else {
            // �Ѿ���½,�����˴�����
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}