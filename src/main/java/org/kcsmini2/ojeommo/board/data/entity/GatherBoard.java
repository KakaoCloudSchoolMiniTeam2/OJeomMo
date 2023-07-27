package org.kcsmini2.ojeommo.board.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.category.entity.Category;
import org.kcsmini2.ojeommo.member.data.entity.Party;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx__bumped_at", columnList = "bumped_at")
})
public class GatherBoard {

    @Id
    @Column(name = "board_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "board_id")
    private Board board;

    @Column
    private String dinerName;

    @Column
    private Integer gatherNumber;

    @Column
    private Integer initNumber;

    @Column
    private Boolean isDelivery;

    @Column(name = "bumped_at")
    private LocalDateTime bumpedAt;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Party> partyList;

    @Builder
    public GatherBoard(Board board, String dinerName, Integer gatherNumber, Integer initNumber, Boolean isDelivery, LocalDateTime bumpedAt, Category category) {
        this.board = board;
        this.dinerName = dinerName;
        this.gatherNumber = gatherNumber;
        this.initNumber = initNumber;
        this.isDelivery = isDelivery;
        this.bumpedAt = bumpedAt;
        this.category = category;
    }

    public void bumped(LocalDateTime bumpedAt) {
        this.bumpedAt = bumpedAt;
    }

    public void update(GatherBoardUpdateRequestDTO requestDTO) {
        this.board.update(requestDTO);
        this.dinerName = requestDTO.getDinerName();
        this.gatherNumber = requestDTO.getGatherNumber();
        this.initNumber = requestDTO.getInitNumber();
        this.isDelivery = requestDTO.getIsDelivery();
        this.bumpedAt = requestDTO.getBumpedAt();
        this.category = requestDTO.getCategory();
    }
}
