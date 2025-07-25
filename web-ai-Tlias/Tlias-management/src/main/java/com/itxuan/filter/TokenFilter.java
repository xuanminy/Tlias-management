package com.itxuan.filter;

import com.itxuan.utils.CurrentHolder;
import com.itxuan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径，并且判断是否为登录请求
        String requestURI = request.getRequestURI();
        log.info("拦截请求：{}", requestURI);
        //2.如果是/login,则不需要Token验证，放行
        if (requestURI.equals("/login")) {
            log.info("登录请求，不需要Token验证");
            filterChain.doFilter(request, response);
            return ;
        }

        //3.获取请求头中的Token
        String token = request.getHeader("Token");
        //4.首先判断是否存在，（错误401），调用JwtUtils中的方法，验证Token，错误（401）
        if(token != null){
            try {
                //5.如果验证成功，则放行
                Claims claims = JwtUtils.parseToken(token);
                Integer empId =  Integer.valueOf(claims.get("id").toString());
                CurrentHolder.setCurrentEmpId(empId);
                filterChain.doFilter(request, response);
                CurrentHolder.remove();
            } catch (Exception e) {
                e.printStackTrace();
                log.info("Token验证失败");
                response.setStatus(401);
                return;
            }
        }else {
            log.info("Token不存在");
            response.setStatus(401);
            return;
        }


    }

}
