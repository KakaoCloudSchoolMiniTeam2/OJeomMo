package org.kcsmini2.ojeommo.board.data.dto.response.detail;

import org.kcsmini2.ojeommo.board.data.entity.Board;

import java.time.LocalDateTime;

/**
*   게시글 상세보기 페이지
*/
public class BoardDetailResponseDTO {
    private Long id;
    private String title;
//    private PostStatus status;//있어야 되지 않을까?
//    private int views;
//    private int likes;
//    private boolean liked;
    private String content;
    private LocalDateTime createdAt;
//    private long updatedAt;
//    private DetailPageAccountResponseDto author;

    protected BoardDetailResponseDTO(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
    }

    public static BoardDetailResponseDTO from(Board board){
        return new BoardDetailResponseDTO(board);
    }
}
