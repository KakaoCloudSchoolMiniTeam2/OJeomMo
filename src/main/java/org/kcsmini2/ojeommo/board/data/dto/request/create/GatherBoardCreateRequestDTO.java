package org.kcsmini2.ojeommo.board.data.dto.request.create;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GatherBoardCreateRequestDTO extends BoardCreateRequestDTO{

    private String dinerName;
    private Integer gatherNumber;
    private Integer initNumber;
    private Boolean isDelivery;
    private LocalDateTime bumpedAt;
    private Category category;

    public GatherBoard toEntity(Board board) {
//        Board board = super.toEntity(member);
        GatherBoard gatherBoard = GatherBoard.builder()
                .board(board)
                .dinerName(dinerName)
                .gatherNumber(gatherNumber)
                .initNumber(initNumber)
                .isDelivery(isDelivery)
                .bumpedAt(bumpedAt)
                .category(category)
                .build();

        return gatherBoard;

    }


}
