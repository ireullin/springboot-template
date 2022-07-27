package com.ireullin.springboottemplate.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHandler  implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        // var user = request.getSession().getAttribute("user");
        var token = request.getHeader("token");
        
        if(token==null){
            return false;
        }
        
        if(jwtUtils.validateToken(token)){
            
        }

        log.debug("user is {}", token);
        return true;
    }
    
}
