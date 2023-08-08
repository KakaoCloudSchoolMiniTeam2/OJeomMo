package org.kcsmini2.ojeommo.member.data.dto;

import lombok.Getter;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@Getter
public class PartyMemberDetailResponseDTO {
    private String nickname;
    private String name;

    public PartyMemberDetailResponseDTO(Member member) {
        this.nickname = member.getNickname();
        this.name = member.getName();
    }

    public static PartyMemberDetailResponseDTO from(Member member) {
        return new PartyMemberDetailResponseDTO(member);
    }
}
