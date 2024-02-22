package fr.mspr_java_b3.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HeaderFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if ("/login".equals(path) || "/register".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        JwtUtil jwtUtil = new JwtUtil();
        Claims claims = jwtUtil.resolveClaims(request);

        try {
            if (claims == null || !jwtUtil.validateClaims(claims)) {
                throw new Exception("Token invalide");
            }
            filterChain.doFilter(request, response);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(jwtUtil.getId(claims), null));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
