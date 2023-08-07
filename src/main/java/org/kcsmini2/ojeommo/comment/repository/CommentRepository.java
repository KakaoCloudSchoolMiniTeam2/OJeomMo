package org.kcsmini2.ojeommo.comment.repository;

import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByBoard(Pageable pageable, Board board);
}
