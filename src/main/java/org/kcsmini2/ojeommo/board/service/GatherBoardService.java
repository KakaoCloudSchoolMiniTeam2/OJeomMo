package org.kcsmini2.ojeommo.board.service;

import jakarta.transaction.Transactional;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface GatherBoardService {


    public void createBoard(BoardCreateRequestDTO requestDTO, MemberDTO memberDTO);

    public BoardDetailResponseDTO readBoard(Long boardId, MemberDTO memberDTO);

    public boolean joinParty(Long boardId, MemberDTO memberDTO);

    public Page<BoardDetailResponseDTO> readBoardPage(Pageable pageable, MemberDTO memberDTO);


}
