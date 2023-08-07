package org.kcsmini2.ojeommo.comment.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;
import org.kcsmini2.ojeommo.member.data.entity.Member;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentUpdateRequestDTO {
    protected Long commentId;
    protected String content;

    public Comment toEntity(Member author, Board board) {
        return Comment.builder()
                .author(author)
                .board(board)
                .content(content)
                .build();
    }
}




