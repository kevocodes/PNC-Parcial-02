package com.kevocodes.pnccontrollers.configurations;

import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.services.UserService;
import com.kevocodes.pnccontrollers.utils.JWTTools;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;

    private final UserService userService;

    public JWTTokenFilter(JWTTools jwtTools, UserService userService) {
        this.jwtTools = jwtTools;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        String path = request.getRequestURI();

        // Rutas que no requieren verificación de JWT
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if(tokenHeader != null && tokenHeader.startsWith("Bearer ") && tokenHeader.length() > 7) {
            token = tokenHeader.substring(7);

            try {
                username = jwtTools.getUsernameFrom(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,"Unable to get JWT Token");
                return;
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,"JWT TOKEN has expired");
                return;
            } catch (MalformedJwtException e) {
                System.out.println("JWT Malformed");
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,"JWT Malformed");
                return;
            } catch (SignatureException e) {
                System.out.println("JWT invalid");
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,"JWT invalid");
                return;
            }
        } else {
            System.out.println("Bearer string not found");
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,"Bearer string not found");
            return;
        }

        if(username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findByIdentifier(username, true);

            if(user != null) {
                Boolean tokenValidity = userService.isTokenValid(user, token);

                if(tokenValidity) {
                    //Preparing the authentication token.
                    UsernamePasswordAuthenticationToken authToken
                            = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    //This line, sets the user to security context to be handled by the filter chain
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);

    }

    private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try {
            response.getWriter().write("{\"error\": \"" + message + "\"}");
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}