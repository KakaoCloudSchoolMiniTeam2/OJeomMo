package org.kcsmini2.ojeommo.comment.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.comment.data.dto.request.CommentUpdateRequestDTO;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@Entity (name= "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx__table", columnList = "board_id")
})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "author_id")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member author;

    @JoinColumn(name = "board_id")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @Column
    private String content;

    @Builder
    public Comment(Member author, Board board, String content) {
        this.author = author;
        this.board = board;
        this.content = content;
    }

    public Comment update(CommentUpdateRequestDTO requestDTO) {
        this.content = requestDTO.getContent();
        return this;
    }

    public boolean isWrongAuthor(Member requester) {
        return !this.author.getId().equals(requester.getId());
    }
}
