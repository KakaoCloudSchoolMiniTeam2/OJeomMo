package org.kcsmini2.ojeommo.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@Entity
@Getter
@NoArgsConstructor
@IdClass(FavoriteCategoryPk.class)
public class FavoriteCategory {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @Builder
    public FavoriteCategory(String memberId, Long categoryId) {
        this.memberId = memberId;
        this.categoryId = categoryId;
    }
}
