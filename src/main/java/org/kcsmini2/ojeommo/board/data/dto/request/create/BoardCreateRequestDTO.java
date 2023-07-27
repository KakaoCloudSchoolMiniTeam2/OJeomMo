package org.kcsmini2.ojeommo.board.data.dto.request.create;

import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardCreateRequestDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public Board toEntity(Member author){
        Board board = Board.builder()
                .author(author)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();

        return board;
    }
}




