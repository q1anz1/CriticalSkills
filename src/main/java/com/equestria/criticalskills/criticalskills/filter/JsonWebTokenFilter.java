
package com.equestria.criticalskills.criticalskills.filter;

import com.equestria.criticalskills.criticalskills.exception.LoginException;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.AccountMapper;
import com.equestria.criticalskills.criticalskills.utils.JsonWebTokenUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
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


            String token = getAccessToken(httpServletRequest);
            if (token != null&&token.length()!=0){
                if (jsonWebTokenUtil.verifyToken(token)){
                    log.info("用户已登录,放行");
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    log.info("用户登录超时,变成游客身份");
                    httpServletRequest.setAttribute("visitor",true);
                    filterChain.doFilter(servletRequest, servletResponse);
                    throw new LoginException("非法jwt令牌");
                }
            }else {
                log.info("未登录,以游客身份访问");
                httpServletRequest.setAttribute("visitor",true);
                filterChain.doFilter(servletRequest, servletResponse);
            }



    }



}

