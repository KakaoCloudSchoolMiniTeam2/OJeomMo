package org.kcsmini2.ojeommo.board.data.dto.request.create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GatherBoardCreateRequestDTO extends BoardCreateRequestDTO{

    @NotNull
    private Integer gatherNumber;

    @NotNull
    private Integer initNumber;

    @NotNull
    private Boolean isDelivery;

    @NotBlank
    private String categoryName;

    @Setter
    private Category category;

    @Builder
    public GatherBoardCreateRequestDTO(String title, String content, String dinerName,
                                       Integer gatherNumber, Integer initNumber, Boolean isDelivery, String categoryName) {
        super(title, content);
        this.gatherNumber = gatherNumber;
        this.initNumber = initNumber;
        this.isDelivery = isDelivery;
        this.categoryName = categoryName;
    }

    public GatherBoard toEntity(Board board) {
//        Board board = super.toEntity(member);
        GatherBoard gatherBoard = GatherBoard.builder()
                .board(board)
                .dinerName(super.title)
                .gatherNumber(gatherNumber)
                .initNumber(initNumber)
                .isDelivery(isDelivery)
                .category(category)
                .build();

        return gatherBoard;
    }
}
