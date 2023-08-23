package org.kcsmini2.ojeommo.board.data.dto.response.detail;

import lombok.Getter;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;

import java.time.LocalDateTime;

@Getter
public class GatherBoardDetailResponseDTO extends BoardDetailResponseDTO {

    private String dinerName;
    private Integer gatherNumber;
    private Integer initNumber;
    private Boolean isDelivery;
    private LocalDateTime bumpedAt;
    private String category;
    private Boolean isJoined;
    private Boolean isMyArticle;

    private GatherBoardDetailResponseDTO(GatherBoard board, boolean isJoined, Integer joinNumber, MemberDTO requester) {
        super(board.getBoard());
        this.dinerName = board.getDinerName();
        this.gatherNumber = board.getGatherNumber();
        this.initNumber = joinNumber;
        this.isDelivery = board.getIsDelivery();
        this.bumpedAt = board.getBumpedAt();
        this.category = board.getCategory().getCategoryName();
        this.isJoined = isJoined;
        this.isMyArticle = board.isSameMember(requester);
    }

    public static GatherBoardDetailResponseDTO from(GatherBoard board, boolean isJoined, Integer joinNumber, MemberDTO requester) {
        return new GatherBoardDetailResponseDTO(board, isJoined, joinNumber, requester);
    }
}
