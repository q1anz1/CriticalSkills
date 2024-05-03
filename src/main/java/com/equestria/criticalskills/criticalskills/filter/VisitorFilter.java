package com.equestria.criticalskills.criticalskills.filter;

import com.equestria.criticalskills.criticalskills.exception.LoginException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
@Order(2)
public class VisitorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");
        var visitor = httpServletRequest.getAttribute("visitor");
        if (visitor==null) {
            log.info("非游客身份,放行");
            filterChain.doFilter(servletRequest, servletResponse);
        }

        String url = httpServletRequest.getRequestURI();
        if (url.equals("/log/send_verify_code")||url.equals("/log/register")||url.equals("/log/login")){
            log.info("此请求属于允许游客访问的请求");
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            log.info("不允许游客访问的请求,需要先登录");
            throw new LoginException("请先登录");
        }

    }

}
