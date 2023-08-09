package org.kcsmini2.ojeommo.board.data.dto.response.detail;

import lombok.Data;
import org.kcsmini2.ojeommo.board.data.entity.Board;

import java.time.LocalDateTime;

/**
*   게시글 상세보기 페이지
*/
@Data
public class BoardDetailResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String authorId;
    private String authorNickname;

    protected BoardDetailResponseDTO(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.authorId = board.getAuthor().getId();
        this.authorNickname = board.getAuthor().getNickname();
    }

    public static BoardDetailResponseDTO from(Board board){
        return new BoardDetailResponseDTO(board);
    }
}
