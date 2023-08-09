package org.kcsmini2.ojeommo.member.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
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
public class UpdateRequest {

    private String id;

    private String pw;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    public boolean isPasswordBlank() {
        return this.getPw() == null || this.getPw().equals("");
    }
}
