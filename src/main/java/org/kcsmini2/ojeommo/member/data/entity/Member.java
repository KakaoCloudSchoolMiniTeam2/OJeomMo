package org.kcsmini2.ojeommo.member.data.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.category.entity.FavoriteCategory;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.SignRequest;
import org.kcsmini2.ojeommo.member.data.dto.UpdateRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 작성자: 김준연
 * <p>
 * 설명: member Entity
 * <p>
 * 최종 수정 일자: 2023/07/31
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String id;

    private String pw;

    private String nickname;

    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FavoriteCategory> favoriteCategories = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }

    public MemberDTO toDTO() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .nickname(this.nickname)
                .pw(this.pw)
                .roles(this.roles)
                .build();
        return memberDTO;
    }

    public boolean updateMember(UpdateRequest request) {
        try {
            if (request.getPw() != null && !request.getPw().equals("")) this.pw = request.getPw();
            this.nickname = request.getNickname();
            this.name = request.getName();
            this.email = request.getEmail();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}