package org.kcsmini2.ojeommo.board.data.dto.request.bumped;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GatherBoardBumpedRequestDTO {
    private LocalDateTime bumpedAt;

    public void bumpedEntity(GatherBoard board){
        board.bumped(bumpedAt);
    }
}
