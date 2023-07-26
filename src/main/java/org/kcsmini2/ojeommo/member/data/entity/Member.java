package org.kcsmini2.ojeommo.member.data.entity;


import jakarta.persistence.*;
import lombok.*;
import org.kcsmini2.ojeommo.category.entity.FavoriteCategory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Member {

    @Id
    private String id;

    private String pw;

    private String nickname;

    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    @OneToMany(mappedBy ="member" , fetch = FetchType.EAGER, cascade= CascadeType.REMOVE)
    private List<FavoriteCategory> favoriteCategories = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }

}