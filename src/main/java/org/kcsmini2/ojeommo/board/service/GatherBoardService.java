package org.kcsmini2.ojeommo.board.service;

import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.BoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface GatherBoardService {
    public void createBoard(GatherBoardCreateRequestDTO requestDTO, MemberDTO memberDTO);

    public GatherBoardDetailResponseDTO readBoard(Long boardId, MemberDTO memberDTO);

    public void deleteBoard(Long boardId, MemberDTO memberDTO);

    public void updateBoard(Long boardId, GatherBoardUpdateRequestDTO requestDTO, MemberDTO memberDTO);

    public void bumpBoard(GatherBoardBumpedRequestDTO gatherBoardBumpedRequestDTO, Long boardId, MemberDTO memberDTO);

    public boolean joinParty(Long boardId, MemberDTO memberDTO);

    public Page<BoardDetailResponseDTO> readBoardPage(Pageable pageable, MemberDTO memberDTO);


}
