package org.kcsmini2.ojeommo.member.service;

import org.kcsmini2.ojeommo.member.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.entity.Member;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 작성자: 김준연
 *
 * 설명: member CRUD + a 서비스
 *
 * 최종 수정 일자: 2023/07/21
 */
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(final MemberDTO memberDTO) {

        //아이디 중복체크 등 유효성 검증 코드 추가 예정

        Member member = Member.builder()
                .id(memberDTO.getId())
                .pw(memberDTO.getPw())
                .nickname(memberDTO.getNickname())
                .email(memberDTO.getEmail())
                .build();
        return memberRepository.save(member);
    }
}
