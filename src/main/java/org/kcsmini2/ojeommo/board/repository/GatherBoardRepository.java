package org.kcsmini2.ojeommo.board.repository;

import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatherBoardRepository extends JpaRepository<GatherBoard, Long> {
    Page<GatherBoard> findAllBy(Pageable pageable);
}
