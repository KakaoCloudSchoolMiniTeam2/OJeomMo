package org.kcsmini2.ojeommo.board.data.dto.request.bumped;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class GatherBoardBumpedRequestDTO {
    private LocalDateTime bumpedAt;

    public void bumpedEntity(GatherBoard gatherBoard){
        gatherBoard.bumped(bumpedAt);
    }
}
