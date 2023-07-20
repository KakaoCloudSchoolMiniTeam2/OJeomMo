package org.kcsmini2.ojummo.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kcsmini2.ojummo.board.entity.Board;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(PartyPk.class)
public class Party {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @Column(name = "board_id")
    private Long boardId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "board_id")
    private Board board;

    @Column
    private LocalDateTime joinedAt;

    @Builder
    public Party(Member member, Board board, LocalDateTime joinedAt) {
        this.member = member;
        this.board = board;
        this.joinedAt = joinedAt;
    }
}
