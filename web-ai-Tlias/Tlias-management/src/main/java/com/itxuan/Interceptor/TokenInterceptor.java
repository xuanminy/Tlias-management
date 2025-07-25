package com.itxuan.Interceptor;

import com.itxuan.utils.CurrentHolder;
import com.itxuan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行前的拦截，放行之前执行的
        String requestURI = request.getRequestURI();
        log.info("拦截请求：{}", requestURI);
        //3.获取请求头中的Token
        String token = request.getHeader("Token");
        //4.首先判断是否存在，（错误401），调用JwtUtils中的方法，验证Token，错误（401）
        if(token != null){
            try {
                //5.如果验证成功，则放行
                Claims claims = JwtUtils.parseToken(token);
                Integer empId =  Integer.valueOf(claims.get("id").toString());
                CurrentHolder.setCurrentEmpId(empId);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                log.info("Token验证失败");
                response.setStatus(401);
                return false;
            }
        }else {
            log.info("Token不存在");
            response.setStatus(401);
            return  false;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //放行后的拦截，放行之后执行的
        //回来之后删除数据
        CurrentHolder.remove();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //视图渲染完毕之后执行，之前前后端不分离就会存在页面的渲染
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
