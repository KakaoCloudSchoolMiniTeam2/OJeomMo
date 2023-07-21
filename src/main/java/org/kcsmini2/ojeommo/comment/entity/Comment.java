package org.kcsmini2.ojeommo.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kcsmini2.ojummo.board.entity.Board;
import org.kcsmini2.ojummo.member.entity.Member;

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

    @JoinColumn(name = "comment_id")
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
