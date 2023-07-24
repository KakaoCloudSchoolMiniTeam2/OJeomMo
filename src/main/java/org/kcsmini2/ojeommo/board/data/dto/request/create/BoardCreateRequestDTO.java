package org.kcsmini2.ojeommo.board.data.dto.request.create;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequestDTO {

    private String title;
    private String content;
    private LocalDateTime createdAt;


    // DTO를 Entity로 변환
    @Builder
    public Board toEntity(Member author){
        // Board board = new Board(author, title, content,createdAt)
        Board board = Board.builder()
                .author(author)

                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();

        return board;
    }







}




