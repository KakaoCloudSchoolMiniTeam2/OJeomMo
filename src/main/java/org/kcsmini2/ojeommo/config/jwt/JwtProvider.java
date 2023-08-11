package org.kcsmini2.ojeommo.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.entity.Authority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 작성자: 김준연
 *
 * 설명: jwt 토큰 생성, 검증, 정보 로딩 클래스
 *
 * 최종 수정 일자: 2023/07/21
 */
@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String salt;

    private Key secretKey;

    // 만료시간 : 1Hour
    private final long exp = 1000L * 60 * 60;

    private final TokenService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String createToken(String id, List<Authority> roles) {
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    public Authentication getAuthentication(String token) {
        MemberDTO memberDTO = userDetailsService.loadUserByUsername(this.getAccount(token));

        return new UsernamePasswordAuthenticationToken(memberDTO, "", memberDTO.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    public String getAccount(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;

        Cookie authorizationCookie = Arrays.stream(cookies)
                .filter(cookie -> Objects.equals(cookie.getName(), "Authorization"))
                .findFirst()
                .orElse(null);
        if (authorizationCookie == null) return null;

        return authorizationCookie.getValue();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
