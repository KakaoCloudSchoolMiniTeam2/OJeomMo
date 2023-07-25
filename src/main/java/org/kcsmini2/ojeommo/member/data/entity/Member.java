package org.kcsmini2.ojeommo.member.data.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

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
    public Member(String id, String pw, String nickname, String name, String email) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
    }

}