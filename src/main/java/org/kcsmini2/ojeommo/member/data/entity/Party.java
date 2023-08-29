package org.kcsmini2.ojeommo.member.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(PartyPk.class)
public class Party {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Id
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GatherBoard board;

    @Column
    @CreationTimestamp
    private LocalDate joinedAt;

    @Builder
    public Party(Member member, GatherBoard board, LocalDate joinedAt) {
        this.memberId = member.getId();
        this.boardId = board.getId();
        this.joinedAt = joinedAt;
    }
}