package org.kcsmini2.ojeommo.board.data.dto.request.delete;

import lombok.Data;

@Data
public class QuitPartyRequestDto {

    private Long boardId;

    public QuitPartyRequestDto (Long boardId) {
        this.boardId = boardId;
    }
}