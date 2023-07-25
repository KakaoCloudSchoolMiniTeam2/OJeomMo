package org.kcsmini2.ojeommo.comment.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private Member author;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private Board board;

    @Column
    private String content;

    @Builder
    public Comment(Member author, Board board, String content) {
        this.author = author;
        this.board = board;
        this.content = content;
    }
}
