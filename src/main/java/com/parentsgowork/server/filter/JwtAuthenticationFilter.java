package com.parentsgowork.server.filter;

import com.parentsgowork.server.apiPayload.exception.JwtAuthenticationException;
import com.parentsgowork.server.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import static com.parentsgowork.server.apiPayload.code.status.ErrorStatus.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = resolveToken(request); //Authorization 헤더에서 토큰 추출

            //토큰이 주어졌는지 확인
            if (token == null) {
                throw new JwtAuthenticationException(MISSING_TOKEN);
            }

            //토큰에서 사용자 정보 읽어서 탈퇴한 회원인지 확인
            if (jwtTokenUtil.isUserInactive(token))
                throw new JwtAuthenticationException(USER_STATUS_INACTIVE);

            //토큰이 유효한지 확인
            if (jwtTokenUtil.validateToken(token)) {
                Authentication authentication = jwtTokenUtil.getAuthentication(token); //토큰에서 사용자 정보 추출
                SecurityContextHolder.getContext().setAuthentication(authentication); //SecurityContext 에 사용자 정보 저장
            }

        } catch (JwtAuthenticationException e) {
            request.setAttribute("errorStatus", e.getErrorStatus()); //CustomAuthenticationEntryPoint 에서 처리
        } catch (ExpiredJwtException e) {
            request.setAttribute("errorStatus", EXPIRED_TOKEN);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorStatus", INVALID_TOKEN_FORMAT);
        } catch (SignatureException e){
            request.setAttribute("errorStatus", INVALID_SIGNATURE);
        } catch (MalformedJwtException e) {
            request.setAttribute("errorStatus", MALFORMED_TOKEN);
        } catch (UnsupportedJwtException e) {
            request.setAttribute("errorStatus", UNSUPPORTED_TOKEN);
        } catch (Exception e) {
            request.setAttribute("errorStatus", INVALID_TOKEN);
        }

        filterChain.doFilter(request, response); //다음 필터로 요청 전달
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7); //"Bearer " 제거

        return null;
    }
}
