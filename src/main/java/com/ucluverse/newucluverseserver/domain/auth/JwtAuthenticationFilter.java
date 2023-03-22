package com.ucluverse.newucluverseserver.domain.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenProvider.resolveToken(request);
        // 토큰이 유효시
        if (token != null && tokenProvider.validateToken(token)) {
            // 토큰으로부터 유저 정보를 받아
            Authentication authentication = tokenProvider.getAuthentication(token);
            // SecurityContext에 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 다음 Filter 실행
        filterChain.doFilter(request, response);
    }
}
