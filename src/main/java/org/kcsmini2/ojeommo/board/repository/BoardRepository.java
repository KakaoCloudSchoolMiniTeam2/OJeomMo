package org.kcsmini2.ojeommo.board.repository;


import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
