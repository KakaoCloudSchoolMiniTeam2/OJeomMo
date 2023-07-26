package org.kcsmini2.ojeommo.comment;

import org.kcsmini2.ojeommo.comment.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
