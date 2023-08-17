package org.kcsmini2.ojeommo.board.data.dto.response.create;

import lombok.Getter;
import org.kcsmini2.ojeommo.board.data.entity.Board;

@Getter
public class BoardCreateResponseDTO {

    private Long id;

    private BoardCreateResponseDTO(Board board){
        this.id = board.getId();
    }

    public static BoardCreateResponseDTO from(Board board){
        return new BoardCreateResponseDTO(board);
    }
}
