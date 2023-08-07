package org.kcsmini2.ojeommo.comment.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;

@Getter
@NoArgsConstructor
public class CommentDetailResponseDTO {

    String content;
    String authorId;

    @Builder
    protected CommentDetailResponseDTO(String content, String authorId) {
        this.content = content;
        this.authorId = authorId;
    }

    public static CommentDetailResponseDTO from(Comment comment) {
        return CommentDetailResponseDTO.builder()
                .content(comment.getContent())
                .authorId(comment.getAuthor().getId())
                .build();
    }
}
