package com.supun.productapi.logger;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CorsSecurityLogger {

    private static final Logger logger = LoggerFactory.getLogger(CorsSecurityLogger.class);

    public void logCorsViolation(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        String userAgent = request.getHeader("User-Agent");
        String ip = getClientIP(request);

        logger.warn("CORS violation detected - Origin: {}, IP: {}, User-Agent: {}",
                origin, ip, userAgent);
    }

    private String getClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        return xForwardedFor != null ? xForwardedFor.split(",")[0] : request.getRemoteAddr();
    }
}