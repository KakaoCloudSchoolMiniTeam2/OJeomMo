package org.kcsmini2.ojeommo.jwt;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kcsmini2.ojeommo.config.jwt.JwtProperties;
import org.kcsmini2.ojeommo.config.jwt.TokenProvider;
import org.kcsmini2.ojeommo.member.entity.Member;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void generateToken() {
        // given  유저 생성 
        Member testMember = memberRepository.save(Member.builder()
                .id("testid")
                .email("user@gmail.com")
                .pw("test")
                .build());

        // when   토큰 생성 
        String token = tokenProvider.generateToken(testMember, Duration.ofDays(14));
        System.out.println("token is " + token);
        // then   토큰 파싱해서 user ID를 구한 후, testUser.getId()와 비교. 
        String userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", String.class);

        assertThat(userId).isEqualTo(testMember.getId());

        memberRepository.deleteById(testMember.getId());
    }

    @DisplayName("그냥 유저넣기 테스트")
    @Test
    void joinMember() {
        // given  유저 생성
        Member testMember = memberRepository.save(Member.builder()
                .id("testid")
                .name("kjy")
                .nickname("kjynick")
                .email("user@gmail.com")
                .pw("test")
                .build());

        assertThat(memberRepository.findById("testid").get().getId()).isEqualTo(testMember.getId());
    }

    @DisplayName("validToken(): 만료된 토큰인 경우에 유효성 검증에 실패한다.")
    @Test
    void validToken_invalidToken() {

        // given   토큰 생성. (만료 시간을 7일 전으로 설정)
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when    토큰 검증
        boolean result = tokenProvider.validToken(token);

        // then    토큰 검증 결과가 false인지 확인
        assertThat(result).isFalse();
    }


    @DisplayName("validToken(): 유효한 토큰인 경우에 유효성 검증에 성공한다.")
    @Test
    void validToken_validToken() {
        // given
        String token = JwtFactory.withDefaultValues()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isTrue();
    }


    @DisplayName("getAuthentication(): 토큰 기반으로 인증정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        // given
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    @DisplayName("getUserId(): 토큰으로 유저 ID를 가져올 수 있다.")
    @Test
    void getUserId() {
        // given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        String userIdByToken = tokenProvider.getUserId(token);

        // then
        assertThat(userIdByToken).isEqualTo(userId);
    }
}