package org.kcsmini2.ojeommo.board.data.dto.request.create;


import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GatherBoardCreateRequestDTO extends BoardCreateRequestDTO{
    private Integer gatherNumber;
    private Integer initNumber;
    private Boolean isDelivery;
    private String category;

    @Builder
    public GatherBoardCreateRequestDTO(String title, String content, String dinerName,
                                       Integer gatherNumber, Integer initNumber, Boolean isDelivery, String category) {
        super(title, content);
        this.gatherNumber = gatherNumber;
        this.initNumber = initNumber;
        this.isDelivery = isDelivery;
        this.category = category;
    }

    public GatherBoard toEntity(Board board) {
//        Board board = super.toEntity(member);
        GatherBoard gatherBoard = GatherBoard.builder()
                .board(board)
                .dinerName(super.title)
                .gatherNumber(gatherNumber)
                .initNumber(initNumber)
                .isDelivery(isDelivery)
                .build();

        return gatherBoard;
    }
}
