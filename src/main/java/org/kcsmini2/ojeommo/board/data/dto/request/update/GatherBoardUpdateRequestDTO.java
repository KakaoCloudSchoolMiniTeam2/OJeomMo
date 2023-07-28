package org.kcsmini2.ojeommo.board.data.dto.request.update;


import lombok.*;
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
    private String categoryName;
    @Setter
    private Category category;

    @Builder
    public GatherBoardUpdateRequestDTO(Long boardId, String title, String content, LocalDateTime createdAt, String dinerName,
                                       Integer gatherNumber, Integer initNumber, Boolean isDelivery, LocalDateTime bumpedAt, String categoryName){
        super(title,content,createdAt,boardId);
        this.dinerName = dinerName;
        this.gatherNumber = gatherNumber;
        this.initNumber = initNumber;
        this.isDelivery = isDelivery;
        this.bumpedAt = bumpedAt;
        this.categoryName = categoryName;
    }
}
