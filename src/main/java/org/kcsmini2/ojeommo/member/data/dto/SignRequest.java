package org.kcsmini2.ojeommo.member.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 작성자: 김준연
 *
 * 설명: Member 엔티티를 Contoller 단에서 사용하기위한 DTO
 *
 * 최종 수정 일자: 2023/07/24
 */
@Getter
@Setter
public class SignRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String pw;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private String[] categoryIds;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .id(this.getId())
                .pw(passwordEncoder.encode(this.getPw()))
                .name(this.getName())
                .nickname(this.getNickname())
                .email(this.getEmail())
                .build();
    }

    public boolean isSamePassword(PasswordEncoder passwordEncoder, Member member) {
        return passwordEncoder.matches(this.getPw(), member.getPw());
    }

    public boolean isPasswordBlank() {
        return this.getPw() == null || this.getPw().equals("");
    }
}
