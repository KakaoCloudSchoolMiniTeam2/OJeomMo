package org.kcsmini2.ojeommo.comment.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;

@Getter
@NoArgsConstructor
public class CommentDetailResponseDTO {

    Long id;
    String content;
    String authorId;

    @Builder
    protected CommentDetailResponseDTO(String content, String authorId, Long id) {
        this.content = content;
        this.authorId = authorId;
        this.id = id;
    }
    public static CommentDetailResponseDTO from(Comment comment) {
        return CommentDetailResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorId(comment.getAuthor().getNickname())
                .build();
    }
}
