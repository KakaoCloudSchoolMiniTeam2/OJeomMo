package org.kcsmini2.ojeommo.member.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


public class PartyPk implements Serializable {

    private Long memberId;

    private Long boardId;

    @Entity
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Member {

        @Id
        private String id;

        @Column
        private String pw;

        @Column
        private String nickname;

        @Column
        private String name;

        @Column
        private String email;

        @Builder
        public Member(String pw, String nickname, String name, String email) {
            this.pw = pw;
            this.nickname = nickname;
            this.name = name;
            this.email = email;
        }

    }
}
