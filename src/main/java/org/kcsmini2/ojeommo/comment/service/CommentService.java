package org.kcsmini2.ojeommo.comment.service;

import org.kcsmini2.ojeommo.comment.data.dto.request.CommentCreateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.dto.request.CommentUpdateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.dto.response.CommentDetailResponseDTO;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface CommentService {
    void createComment(CommentCreateRequestDTO requestDTO, MemberDTO memberDTO);

    Page<CommentDetailResponseDTO> readComments(Long boardId, Pageable pageable, MemberDTO memberDTO);

    void updateComment(CommentUpdateRequestDTO requestDTO, MemberDTO memberDTO);

    void deleteComment(Long boardId, MemberDTO memberDTO);

}
