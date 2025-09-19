package com.example.copywritingdemo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HttpSession session = request.getSession();

                // 从 session 中取 currentUser，而不是 user
                if (session.getAttribute("currentUser") == null
                        && !request.getRequestURI().startsWith("/login")
                        && !request.getRequestURI().startsWith("/register")
                        && !request.getRequestURI().startsWith("/css")
                        && !request.getRequestURI().startsWith("/js")) {
                    response.sendRedirect("/login");
                    return false;
                }
                return true;
            }
        }).addPathPatterns("/**");
    }
}
