package org.kcsmini2.ojeommo.member.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.kcsmini2.ojeommo.member.data.entity.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 작성자: 김준연
 *
 * 설명: Member 엔티티를 Contoller 단에서 사용하기위한 DTO
 *
 * 최종 수정 일자: 2023/07/24
 */
@NoArgsConstructor
@Getter
@Setter
public class MemberDTO {
    private String token;
    @NotBlank
    private String id;

    private String pw;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private List<Authority> roles = new ArrayList<>();

    @Builder
    public MemberDTO(String token, String id, String pw, String nickname, String name, String email, List<Authority> roles) {
        this.token = token;
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream().map(o -> new SimpleGrantedAuthority(
                o.getName()
        )).collect(Collectors.toList());
    }

    public String getPassword() {
        return this.getPw();
    }

    public String getUsername() {
        return this.getName();
    }
}