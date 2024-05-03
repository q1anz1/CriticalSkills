package com.equestria.criticalskills.criticalskills.filter;

import com.equestria.criticalskills.criticalskills.exception.LoginException;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.AccountMapper;
import com.equestria.criticalskills.criticalskills.utils.JsonWebTokenUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(1)
public class JsonWebTokenFilter implements Filter {
    private final JsonWebTokenUtil jsonWebTokenUtil;

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.hasText(accessToken)){
            String[] tokens = accessToken.split(" ");
            if (tokens.length == 2){
                if (tokens[0].equals("Bearer")){
                    return tokens[1];
                }
            }
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");

        String url = httpServletRequest.getRequestURI();

        if (url.equals("/register")||url.equals("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String token = getAccessToken(httpServletRequest);
            if (token != null&&token.length()!=0){
                if (jsonWebTokenUtil.verifyToken(token)){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    httpServletRequest.setAttribute("visitor",true);
                    filterChain.doFilter(servletRequest, servletResponse);
                    throw new LoginException("非法jwt令牌");
                }
            }else {
                httpServletRequest.setAttribute("visitor",true);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }


    }



}
