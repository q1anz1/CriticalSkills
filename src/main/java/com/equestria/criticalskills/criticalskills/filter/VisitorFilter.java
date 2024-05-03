package com.equestria.criticalskills.criticalskills.filter;

import com.equestria.criticalskills.criticalskills.exception.LoginException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class VisitorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");
        var visitor = httpServletRequest.getAttribute("visitor");

        if (visitor==null) {filterChain.doFilter(servletRequest, servletResponse);}

        String url = httpServletRequest.getRequestURI();
        if (url.equals("/register")||url.equals("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            throw new LoginException("请先登录");
        }

    }

}
