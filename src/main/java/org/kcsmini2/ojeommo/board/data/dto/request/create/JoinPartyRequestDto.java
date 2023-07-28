package org.kcsmini2.ojeommo.board.data.dto.request.create;

import lombok.Data;

@Data
public class JoinPartyRequestDto {

    private Long boardId;

    public JoinPartyRequestDto (Long boardId) {
        this.boardId = boardId;
    }
}
