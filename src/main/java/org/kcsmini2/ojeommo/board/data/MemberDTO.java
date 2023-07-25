package org.kcsmini2.ojeommo.board.data;

import lombok.*;
import org.kcsmini2.ojeommo.member.data.entity.Member;

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

    public MemberDTO(Member member) {
//        this.token = member.token;
        this.id = member.getId();
        this.pw = member.getPw();
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.email = member.getEmail();
    }

    public static MemberDTO from(Member member) {
        return new MemberDTO(member);
    }
}