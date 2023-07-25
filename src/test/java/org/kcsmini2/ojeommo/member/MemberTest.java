package org.kcsmini2.ojeommo.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kcsmini2.ojeommo.config.jwt.JwtProperties;
import org.kcsmini2.ojeommo.config.jwt.TokenProvider;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 작성자: 김준연
 *
 * 설명: Member 테이블에 대한 CRUD 테스트
 *
 * 최종 수정 일자: 2023/07/21
 */
@SpringBootTest
class MemberTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("멤버 생성 테스트")
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

    @DisplayName("멤버 삭제 테스트")
    @Test
    void deleteMember() {
        //위 생성 테스트로 id가 testid인 member가 테이블에 존재한다 가정.
        memberRepository.deleteById("testid");

        assertThat(memberRepository.findById("testid").isPresent()).isEqualTo(false);
    }

}