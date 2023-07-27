package org.kcsmini2.ojeommo.config.jwt;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final MemberRepository memberRepository;

    public MemberDTO loadUserByUsername(String username) {

        Member member = memberRepository.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication!")
        );

        return member.toDTO();
    }
}
