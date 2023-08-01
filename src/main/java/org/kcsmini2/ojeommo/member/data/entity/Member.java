package org.kcsmini2.ojeommo.member.data.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.kcsmini2.ojeommo.category.entity.FavoriteCategory;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.SignRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * 작성자: 김준연
 *
 * 설명: member Entity
 *
 * 최종 수정 일자: 2023/07/31
 */
@Entity
@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Member{

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

    @OneToMany(mappedBy ="member" , fetch = FetchType.EAGER, cascade= CascadeType.ALL)
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

    public boolean updateMember(SignRequest request) {
        try {
            if(request.getPw() != null && !request.getPw().equals("")) this.pw = request.getPw();
            this.nickname = request.getNickname();
            this.name = request.getName();
            this.email = request.getEmail();

            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

}