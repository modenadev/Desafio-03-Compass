package io.github.modenadev.userproject.jwt.security.authentication;

import io.github.modenadev.userproject.config.SecurityConfiguration;
import io.github.modenadev.userproject.jwt.security.authentication.userdetails.UserDetailsImpl;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Verifica se o endpoint requer autenticação
            if (checkIfEndpointIsNotPublic(request)) {
                String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization
                if (token != null) {
                    String subject = jwtTokenService.getSubjectFromToken(token);
                    User user = userRepository.findByUsername(subject)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

                    UserDetailsImpl userDetails = new UserDetailsImpl(user);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is absent");
                    return; // Encerrar o processamento se não houver token
                }
            }
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }



    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null; // Retorna null se não houver um token válido
    }



    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .noneMatch(endpoint -> requestURI.startsWith(endpoint));
    }
}
