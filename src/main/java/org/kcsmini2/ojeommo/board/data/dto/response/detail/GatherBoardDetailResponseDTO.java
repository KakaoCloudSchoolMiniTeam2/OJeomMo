package org.kcsmini2.ojeommo.board.data.dto.response.detail;

import lombok.Getter;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;

import java.time.LocalDateTime;
@Getter
public class GatherBoardDetailResponseDTO extends BoardDetailResponseDTO{

    private String dinerName;
    private Integer gatherNumber;
    private Integer initNumber;
    private Boolean isDelivery;
    private LocalDateTime bumpedAt;
    private String category;
    private boolean isJoined;

    private GatherBoardDetailResponseDTO(GatherBoard board, boolean isJoined){
        super(board.getBoard());
        this.dinerName = board.getDinerName();
        this.gatherNumber = board.getGatherNumber();
        this.initNumber = board.getInitNumber();
        this.isDelivery = board.getIsDelivery();
        this.bumpedAt = board.getBumpedAt();
        this.category = board.getCategory().getCategory();
        this.isJoined = isJoined;
    }

    public static GatherBoardDetailResponseDTO from(GatherBoard board, boolean isJoined){
        return new GatherBoardDetailResponseDTO(board, isJoined);
    }
}
