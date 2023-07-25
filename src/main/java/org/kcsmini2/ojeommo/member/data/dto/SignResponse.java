package org.kcsmini2.ojeommo.member.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.member.data.entity.Authority;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignResponse {

    private String id;

    private String name;

    private String nickname;

    private String email;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.roles = member.getRoles();
    }

}
