package fr.mspr_java_b3.security;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull jakarta.servlet.http.HttpServletRequest request, @NonNull jakarta.servlet.http.HttpServletResponse response,@NonNull jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {

        String origin = request.getHeader("Origin");
        if ("http://localhost:3000".equals(origin) || "https://tom-chanson.github.io/MSPR1_FRONT_B3/".equals(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600");
        }
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}