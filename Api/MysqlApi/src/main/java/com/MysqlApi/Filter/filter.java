package com.MysqlApi.Filter;

import com.MysqlApi.JWT.Jwtservice;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;


@Component
@WebFilter(urlPatterns = "/*")
public class filter implements Filter {
    @Autowired
    Jwtservice jwtservice;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getRequestURI().equals("/login/match")||request.getRequestURI().equals("/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(Objects.equals(request.getRequestURI(), "/check")){
            String token=request.getHeader("token");
            String username=request.getHeader("username");
            try{
                if(jwtservice.checkJWT(token,username)){
                    response.setStatus(200);
                }else {
                    response.setStatus(403);
                }
            }
            catch (Exception e){
                response.setStatus(403);
            }
            return;
        }
        try {
            String token=request.getHeader("token");
            String username=request.getHeader("username");
            if(jwtservice.checkJWT(token,username)){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                response.setStatus(403);
            }
        }catch (Exception e){
            response.setStatus(403);
        }
    }
}
