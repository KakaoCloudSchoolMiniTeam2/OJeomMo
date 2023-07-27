package org.kcsmini2.ojeommo.board.data.dto.request.update;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.category.entity.Category;
import org.kcsmini2.ojeommo.member.data.entity.Member;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardUpdateRequestDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;

}




