package org.kcsmini2.ojeommo.board.data.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardCreateRequestDTO {

    @NotBlank
    protected String title;
    @NotBlank
    protected String content;

    public Board toEntity(Member author){
        Board board = Board.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();

        return board;
    }
}




