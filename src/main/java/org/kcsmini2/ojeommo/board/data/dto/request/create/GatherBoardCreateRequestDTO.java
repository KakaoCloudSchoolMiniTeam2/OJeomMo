package org.kcsmini2.ojeommo.board.data.dto.request.create;


import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class GatherBoardCreateRequestDTO extends BoardCreateRequestDTO{

    private String dinerName;
    private Integer gatherNumber;
    private Integer initNumber;
    private Boolean isDelivery;
    private LocalDateTime bumpedAt;
    private Category category;


    @Builder
    public GatherBoardCreateRequestDTO(String title, String content, LocalDateTime createdAt, String dinerName,
                                       Integer gatherNumber, Integer initNumber, Boolean isDelivery, LocalDateTime bumpedAt, Category category) {
        super(title, content, createdAt);
        this.dinerName = dinerName;
        this.gatherNumber = gatherNumber;
        this.initNumber = initNumber;
        this.isDelivery = isDelivery;
        this.bumpedAt = bumpedAt;
        this.category = category;
    }



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
