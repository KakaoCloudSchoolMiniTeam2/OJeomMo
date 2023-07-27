package org.kcsmini2.ojeommo.board.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@NoArgsConstructor
@Getter
@Setter
public class MemberDTO {
    private String token;
    private String id;
    private String pw;
    private String nickname;
    private String name;
    private String email;

    @Builder
    public MemberDTO(String token, String id, String pw, String nickname, String name, String email) {
        this.token = token;
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
    }


    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .pw(member.getPw())
                .nickname(member.getNickname())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}