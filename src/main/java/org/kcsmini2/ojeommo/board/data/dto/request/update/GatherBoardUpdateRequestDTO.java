package org.kcsmini2.ojeommo.board.data.dto.request.update;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GatherBoardUpdateRequestDTO extends BoardUpdateRequestDTO {

    private String dinerName;
    private Integer gatherNumber;
    private Integer initNumber;
    private Boolean isDelivery;
    private LocalDateTime bumpedAt;
    private Category category;

    @Builder
    public GatherBoardUpdateRequestDTO(String title, String content, LocalDateTime createdAt, String dinerName,
                                       Integer gatherNumber, Integer initNumber, Boolean isDelivery, LocalDateTime bumpedAt, Category category){
        super(title,content,createdAt);
        this.dinerName = dinerName;
        this.gatherNumber = gatherNumber;
        this.initNumber = initNumber;
        this.isDelivery = isDelivery;
        this.bumpedAt = bumpedAt;
        this.category = category;

    }

    public void updateEntity(GatherBoard gatherBoard){
        gatherBoard.update(dinerName, gatherNumber, initNumber, isDelivery, bumpedAt, category);
    }
}
