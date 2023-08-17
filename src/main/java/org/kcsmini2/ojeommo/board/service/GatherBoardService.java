package org.kcsmini2.ojeommo.board.service;

import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.BoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.create.BoardCreateResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.CategoryResponseDto;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface GatherBoardService {
    public BoardCreateResponseDTO createBoard(GatherBoardCreateRequestDTO requestDTO, MemberDTO memberDTO);

    public GatherBoardDetailResponseDTO readBoard(Long boardId, MemberDTO memberDTO);

    public void deleteBoard(Long boardId, MemberDTO memberDTO);

    public void updateBoard(GatherBoardUpdateRequestDTO requestDTO, MemberDTO memberDTO);

    public void bumpBoard(Long boardId, MemberDTO memberDTO);

    public Page<BoardDetailResponseDTO> readBoardPage(Pageable pageable, MemberDTO memberDTO);

    public Page<BoardDetailResponseDTO> readMyBoardPage(Pageable pageable, MemberDTO memberDTO);

    public void checkPermission(Long boardId, MemberDTO memberDTO);

    public CategoryResponseDto getCategory();
}
