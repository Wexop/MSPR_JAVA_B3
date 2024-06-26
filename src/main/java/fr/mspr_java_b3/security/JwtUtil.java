package fr.mspr_java_b3.security;

import fr.mspr_java_b3.entities.Utilisateur;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {


    private final String secret_key = "QTo5RQ2p0E";
    private long accessTokenValidity = 24 * 60 * 60 * 1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(Utilisateur user) {
        Claims claims = Jwts.claims().setSubject(user.getMail());
        claims.put("UserId", user.getId());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public Claims resolveClaimsWebsocket(StompHeaderAccessor accessor) {
        try {
            String token = resolveTokenWebsocket(accessor);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;

        } catch (Exception ex) {
            return null;
        }
    }

    public String resolveTokenWebsocket(StompHeaderAccessor accessor) {
        String bearerToken = accessor.getFirstNativeHeader(TOKEN_HEADER);
        return tokenResolver(bearerToken);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header Name: " + headerName + ", Value: " + request.getHeader(headerName));
        }

        return tokenResolver(bearerToken);
    }

    private String tokenResolver(String bearerToken) {
        System.out.println("Bearer Token: " + bearerToken);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    public String getId(Claims claims) {
        return claims.get("UserId").toString();
    }


}