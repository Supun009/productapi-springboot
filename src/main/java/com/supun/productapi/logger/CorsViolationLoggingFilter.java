package com.supun.productapi.logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class CorsViolationLoggingFilter extends OncePerRequestFilter {

    private final CorsSecurityLogger corsSecurityLogger;

    @Autowired
    public CorsViolationLoggingFilter(CorsSecurityLogger corsSecurityLogger) {
        this.corsSecurityLogger = corsSecurityLogger;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String origin = request.getHeader("Origin");
        if (origin != null && !origin.equals("http://localhost:8000")) {
            corsSecurityLogger.logCorsViolation(request);
        }
        filterChain.doFilter(request, response);
    }
}

