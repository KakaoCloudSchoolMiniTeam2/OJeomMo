package org.kcsmini2.ojeommo.board.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.dto.responese.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.responese.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.member.data.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface BoardService {

    BoardDetailResponseDTO getDetail(Long boardId, MemberDTO memberDTO);

}
