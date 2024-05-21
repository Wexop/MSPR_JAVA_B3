package fr.mspr_java_b3.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Component
public class HeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        List<String> noAuthRequiredList = List.of("/login", "/register", "swagger", "/v3/api-docs", "/article_by_id/", "/article/all", "/websocket");

        boolean noAuth = false;

        for (String string : noAuthRequiredList) {
            if (path.contains(string)) {
                noAuth = true;
                break;
            }
        }

        if (noAuth) {
            filterChain.doFilter(request, response);
            return;
        }

        JwtUtil jwtUtil = new JwtUtil();
        Claims claims = jwtUtil.resolveClaims(request);

        try {
            if (claims == null || !jwtUtil.validateClaims(claims)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalid");
            }
            request.setAttribute("Utilisateur_id", jwtUtil.getId(claims));
            filterChain.doFilter(request, response);


        } catch (Exception e) {
            response.sendError(401, "Token invalid");
        }
    }
}
