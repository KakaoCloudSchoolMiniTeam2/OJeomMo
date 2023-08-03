package org.kcsmini2.ojeommo.comment.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.comment.data.dto.request.CommentCreateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.dto.request.CommentUpdateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.dto.response.CommentDetailResponseDTO;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;
import org.kcsmini2.ojeommo.comment.repository.CommentRepository;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 작성자: 김태민
 * <p>
 * 설명: 댓글의 CRUD 기능을 처리하는 서비스 계층
 * <p>
 * 최종 수정 일자: 2023.08.03
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createComment(CommentCreateRequestDTO requestDTO, MemberDTO memberDTO) {
        Member author = memberRepository.getReferenceById(memberDTO.getId());
        Board board = boardRepository.getReferenceById(requestDTO.getBoardId());
        Comment newComment = requestDTO.toEntity(author, board);
        commentRepository.save(newComment);
    }

    @Override
    public Page<CommentDetailResponseDTO> readComments(Long boardId, Pageable pageable, MemberDTO memberDTO) {
        Board board = boardRepository.getReferenceById(boardId);
        Page<Comment> foundComments =  commentRepository.findByBoard(pageable, board);
        return foundComments.map(CommentDetailResponseDTO::from);
    }

    @Override
    public void updateComment(CommentUpdateRequestDTO requestDTO, MemberDTO memberDTO) {
        Member requester = memberRepository.getReferenceById(memberDTO.getId());
        Comment foundComment = commentRepository.findById(requestDTO.getCommentId())
                .orElseThrow(() -> new RuntimeException("해당하는 ID의 댓글이 존재하지 않습니다."));
        if (foundComment.isWrongAuthor(requester)) {
            throw new RuntimeException("요청자와 작성자가 다릅니다.");
        }
        foundComment.update(requestDTO);
    }

    @Override
    public void deleteComment(Long commentId, MemberDTO memberDTO) {
        Member requester = memberRepository.getReferenceById(memberDTO.getId());
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("해당하는 ID의 댓글이 존재하지 않습니다."));
        if (foundComment.isWrongAuthor(requester)) {
            throw new RuntimeException("요청자와 작성자가 다릅니다.");
        }
        commentRepository.delete(foundComment);
    }


}
