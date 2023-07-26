package org.kcsmini2.ojeommo.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kcsmini2.ojeommo.board.entity.Board;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteCategory {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "category_id")
    private Category category;
}
