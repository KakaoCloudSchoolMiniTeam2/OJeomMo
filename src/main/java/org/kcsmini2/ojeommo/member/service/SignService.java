package org.kcsmini2.ojeommo.member.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.category.entity.FavoriteCategory;
import org.kcsmini2.ojeommo.config.jwt.JwtProvider;
import org.kcsmini2.ojeommo.exception.ApplicationException;
import org.kcsmini2.ojeommo.exception.ErrorCode;
import org.kcsmini2.ojeommo.member.data.dto.SignRequest;
import org.kcsmini2.ojeommo.member.data.dto.SignResponse;
import org.kcsmini2.ojeommo.member.data.entity.Authority;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.kcsmini2.ojeommo.member.repository.FavoriteCategoryRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * 작성자: 김준연
 * <p>
 * 설명: member CRUD + a 서비스
 * <p>
 * 최종 수정 일자: 2023/07/31
 */
@Service
@Transactional
@RequiredArgsConstructor
@EnableWebSecurity
public class SignService {

    private final MemberRepository memberRepository;
    private final FavoriteCategoryRepository favoriteCategoryRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean register(SignRequest request) throws Exception {

        if (memberRepository.existsById(request.getId())) throw new ApplicationException(ErrorCode.DUPLICATED_ID);
        else if (memberRepository.findByEmail(request.getEmail()).isPresent())
            throw new ApplicationException(ErrorCode.DUPLICATED_EMAIL);

        Member member = Member.builder()
                .id(request.getId())
                .pw(passwordEncoder.encode(request.getPw()))
                .name(request.getName())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .build();

        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

        memberRepository.save(member);


        for (String str : request.getCategoryIds()) {
            Long id = Long.parseLong(str);
            FavoriteCategory favoriteCategory = FavoriteCategory.builder()
                    .categoryId(id)
                    .memberId(request.getId())
                    .build();
            favoriteCategoryRepository.save(favoriteCategory);
        }


        return true;
    }

    public SignResponse login(SignRequest request) throws Exception {
        Member member = memberRepository.findById(request.getId()).orElseThrow(() ->
                new ApplicationException(ErrorCode.NONEXISTENT_ID));

        if (!passwordEncoder.matches(request.getPw(), member.getPw())) {
            throw new ApplicationException(ErrorCode.UNCORRECTED_PW);
        }

        return SignResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getId(), member.getRoles()))
                .build();

    }

    public boolean update(SignRequest request) {

        if(memberRepository.findByEmailWithOutSelf(request.getId(), request.getEmail()).isPresent()) {
            throw new ApplicationException(ErrorCode.DUPLICATED_EMAIL);
        }

        if (request.getPw() != null && !request.getPw().equals("")) {
            request.setPw(passwordEncoder.encode(request.getPw()));
        }
        Optional<Member> member = memberRepository.findById(request.getId());

        return member.map(value -> value.updateMember(request)).orElse(false);
    }

    public boolean delete(String id) {
        try {
            memberRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
