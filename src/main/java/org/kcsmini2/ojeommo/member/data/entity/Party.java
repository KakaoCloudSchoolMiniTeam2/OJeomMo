package org.kcsmini2.ojeommo.member.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.member.data.entity.Member;

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
    private Member member;

    @Id
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "board_id")
    private GatherBoard board;

    @Column
    private LocalDateTime joinedAt;

    @Builder
    public Party(Member member, GatherBoard board, LocalDateTime joinedAt) {
        this.member = member;
        this.board = board;
        this.joinedAt = joinedAt;
    }
}