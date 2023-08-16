package org.kcsmini2.ojeommo.board.repository;

import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface GatherBoardRepository extends JpaRepository<GatherBoard, Long> {
    Page<GatherBoard> findAllByBumpedAtAfterOrderByBumpedAtDesc(Pageable pageable, LocalDateTime bumpedAt);

    Page<GatherBoard> findAllByBoard_Author_Id(String authorId, Pageable pageable);
}
