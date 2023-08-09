package org.kcsmini2.ojeommo.member.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.category.entity.FavoriteCategory;
import org.kcsmini2.ojeommo.config.jwt.JwtProvider;
import org.kcsmini2.ojeommo.exception.ApplicationException;
import org.kcsmini2.ojeommo.exception.ErrorCode;
import org.kcsmini2.ojeommo.member.data.dto.SignRequest;
import org.kcsmini2.ojeommo.member.data.dto.SignResponse;
import org.kcsmini2.ojeommo.member.data.dto.UpdateRequest;
import org.kcsmini2.ojeommo.member.data.entity.Authority;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.repository.FavoriteCategoryRepository;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean register(SignRequest request) throws Exception {

        if (memberRepository.existsById(request.getId())) throw new ApplicationException(ErrorCode.DUPLICATED_ID);

        if (memberRepository.existsByEmail(request.getEmail()))
            throw new ApplicationException(ErrorCode.DUPLICATED_EMAIL);

        Member member = request.toEntity(passwordEncoder);

        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

        memberRepository.save(member);

        Arrays.stream(request.getCategoryIds())
                .map(Long::parseLong)
                .map(categoryId -> FavoriteCategory.builder()
                        .categoryId(categoryId)
                        .memberId(request.getId())
                        .build())
                .forEach(favoriteCategoryRepository::save);


        return true;
    }

    public SignResponse login(SignRequest request) throws Exception {
        Member member = memberRepository.findById(request.getId()).orElseThrow(() ->
                new ApplicationException(ErrorCode.ID_NOT_FOUND));

        if (!request.isSamePassword(passwordEncoder, member)) {
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

    public boolean update(UpdateRequest request) {

        if (memberRepository.existsByEmailAndIdNot(request.getEmail(), request.getId())) {
            throw new ApplicationException(ErrorCode.DUPLICATED_EMAIL);
        }

        if (!request.isPasswordBlank()) {
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
