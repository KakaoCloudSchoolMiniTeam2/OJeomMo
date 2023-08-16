package org.kcsmini2.ojeommo.member.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[^\\s]*$", message = "pw 내 공백은 허용되지 않습니다.")
    private String pw;
    @NotBlank(message = "닉네임을 작성해주세요.")
    @Pattern(regexp = "^[^\\s]*$", message = "닉네임 내 공백은 허용되지 않습니다.")
    private String nickname;
    @NotBlank(message = "이름을 작성해주세요.")
    @Pattern(regexp = "^[가-힣]{2,6}$", message = "이름은 한글 2~6글자여야 합니다.")
    @Pattern(regexp = "^[^\\s]*$", message = "이름 내 공백은 허용되지 않습니다.")
    private String name;
    @NotBlank(message = "이메일을 작성해주세요.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    private String email;

    public boolean isPasswordBlank() {
        return this.getPw() == null || this.getPw().equals("");
    }
}
